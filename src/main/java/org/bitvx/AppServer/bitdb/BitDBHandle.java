package org.bitvx.AppServer.bitdb;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.bitvx.AppServer.utils.AdvInputStream;
import static org.bitvx.AppServer.utils.AdvInputStream.INT_SIZE;
import static org.bitvx.AppServer.utils.AdvInputStream.LAST;
import static org.bitvx.AppServer.utils.ByteUtils.byteRegionToInt;
import static org.bitvx.AppServer.utils.ByteUtils.byteRegionToString;
import org.bitvx.AppServer.utils.DoubleInteger;

public class BitDBHandle {
    private final AdvInputStream stream;
    private final Map<String, DoubleInteger> tables = new HashMap<>();
    private static final byte[] MAGIC_NUMBER = "VXDB".getBytes(StandardCharsets.US_ASCII);
    private int indexOffest, indexLength, dataRegionOffest;
    private byte[] indexRegion;
    private byte[] dataRegion;

    public BitDBHandle(AdvInputStream stream) throws IOException, IllegalArgumentException {
        this.stream = stream;
        // 基本变量定义
        int indexPtr, streamPtr = 0;

        String tableName;
        int lengthOfTableName, offestOfTable, tableLength;

        if (stream.readToByteArray(LAST, INT_SIZE) != MAGIC_NUMBER) {
            throw new IllegalArgumentException();
        }

        indexOffest = stream.readToInt(streamPtr);
        streamPtr += 4;
        indexLength = stream.readToInt(streamPtr);
        streamPtr += 4;
        dataRegionOffest = stream.readToInt(streamPtr);
        indexRegion = stream.readToByteArray(indexOffest, indexLength);
        dataRegion = stream.readToByteArray(dataRegionOffest, stream.available() - dataRegionOffest);

        indexPtr = 0;

        while ((indexLength - indexPtr) != 0) {
            lengthOfTableName = byteRegionToInt(indexRegion, indexPtr, indexPtr + INT_SIZE);
            indexPtr += INT_SIZE;
            offestOfTable = byteRegionToInt(indexRegion, indexPtr, indexPtr + INT_SIZE);
            indexPtr += INT_SIZE;
            tableLength = byteRegionToInt(indexRegion, indexPtr, indexPtr + INT_SIZE);
            indexPtr += INT_SIZE;
            tableName = byteRegionToString(indexRegion, indexPtr, indexPtr + lengthOfTableName);
            indexPtr += lengthOfTableName;
            tables.put(tableName, new DoubleInteger(offestOfTable, tableLength));
        }

    }

    public BitDBTable getTable(String name) throws IOException {
        return new BitDBTable(dataRegion, dataRegionOffest, tables.get(name));
    }
}