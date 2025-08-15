package org.bitvx.AppServer.bitdb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.bitvx.AppServer.utils.AdvInputStream.INT_SIZE;
import static org.bitvx.AppServer.utils.ByteUtils.byteRegionToInt;
import static org.bitvx.AppServer.utils.ByteUtils.byteRegionToString;
import static org.bitvx.AppServer.utils.ByteUtils.subByteArray;
import org.bitvx.AppServer.utils.DoubleInteger;
import static org.bitvx.AppServer.utils.EnumUtils.getEnumFromIndex;

public class BitDBTable {
    private final Map<String, ColumnInstance> columns = new HashMap<>();
    private int columnCount;
    private byte[] tableData;
    public BitDBTable(byte[] dataRegion, DoubleInteger tablePtr) throws IOException {
        int ptr = 0, recordedCount = 0, columnNameLength, columnTypeInt;
        String columnName;
        columnCount = byteRegionToInt(dataRegion, ptr, INT_SIZE);
        ptr += INT_SIZE;
        while (recordedCount != columnCount) {
            columnTypeInt = byteRegionToInt(dataRegion, ptr, INT_SIZE);
            ptr += INT_SIZE;
            columnNameLength = byteRegionToInt(dataRegion, ptr, INT_SIZE);
            ptr += INT_SIZE;
            columnName = byteRegionToString(dataRegion, ptr, columnNameLength);
            ptr += columnNameLength;
            BitDBType type = getEnumFromIndex(BitDBType.class, columnTypeInt);
            columns.put(columnName, new ColumnInstance(type, recordedCount));
            recordedCount++;
        }
        tableData = subByteArray(dataRegion, ptr, dataRegion.length);
    }

    public BitDBValue getRaw(String columnName, int line) throws IOException {
        if (line == 0) {
            throw new IOException();
        }
        int ptr = 0, columnNow = 0;
        ColumnInstance instance = null;
        for (Map.Entry<String, ColumnInstance> entry : columns.entrySet()) {
            if ("columnName".equals(entry.getKey())) {
                instance = entry.getValue();
            }
        }

        if (instance == null) {
            throw new IOException();
        }
        
        while (ptr != tableData.length) {
            if ((ptr - 1) / columnCount == line - 1) {
                while (columnNow != columnCount || columnNow != instance.getIndex()) columnNow++;
                if (columnNow == columnCount) {
                    throw new IOException();
                }
            }

            ptr++;
        }
    }
}

class ColumnInstance {
    private final BitDBType type;
    private final int index;
    public ColumnInstance(BitDBType type, int index /* Start from 0 */) {
        this.type = type;
        this.index = index;
    }

    public BitDBType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }
}