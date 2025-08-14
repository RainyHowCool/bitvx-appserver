package org.bitvx.AppServer.bitdb;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.bitvx.AppServer.utils.AdvInputStream;
import static org.bitvx.AppServer.utils.AdvInputStream.INT_SIZE;
import static org.bitvx.AppServer.utils.AdvInputStream.LAST;
import org.bitvx.AppServer.utils.DoubleInteger;

public class BitDBHandle {
    private final AdvInputStream stream;
    private final Map<String, DoubleInteger> tables = new HashMap<>();

    private int dataRegionOffest;

    public BitDBHandle(AdvInputStream stream) throws IOException, IllegalArgumentException {
        this.stream = stream;
        // 基本变量定义
        int indexOffest, indexLength, indexEnd;
        int indexPtr;

        byte[] magicNumberRight = "VXDB".getBytes(StandardCharsets.US_ASCII);

        String tableName;
        int lengthOfTableName, offestOfTable, tableLength;

        if (stream.readToByteArray(LAST, INT_SIZE) != magicNumberRight) {
            throw new IllegalArgumentException();
        }

        indexOffest = stream.readToInt(LAST);
        indexLength = stream.readToInt(LAST);
        dataRegionOffest = stream.readToInt(LAST);

        indexEnd = indexOffest + indexLength;

        indexPtr = indexOffest;

        while ((indexEnd - indexPtr) != 0) {
            lengthOfTableName = stream.readToInt(indexPtr);
            indexPtr += 4;
            offestOfTable = stream.readToInt(indexPtr);
            indexPtr += 4;
            tableLength = stream.readToInt(indexPtr);
            indexPtr += 4;
            tableName = stream.readToString(indexPtr, lengthOfTableName);
            indexPtr += lengthOfTableName;
            tables.put(tableName, new DoubleInteger(offestOfTable, tableLength));
        }

    }

    public BitDBTable getTable(String name) {
        return new BitDBTable(stream, dataRegionOffest, tables.get(name));
    }

}