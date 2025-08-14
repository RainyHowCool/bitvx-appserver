package org.bitvx.AppServer.bitdb;

import java.io.IOException;
import java.util.ArrayList;

import org.bitvx.AppServer.utils.AdvInputStream;
import org.bitvx.AppServer.utils.DoubleInteger;

public class BitDBTable {
    private AdvInputStream stream;
    private ArrayList<BitDBType> columns;
    private int columnLength = 0;
    private byte[] tableData;
    public BitDBTable(AdvInputStream stream, int dataRegionOffest, DoubleInteger tablePtr) throws IOException {
        tableData = stream.readToByteArray(dataRegionOffest + tablePtr.getFirst(), tablePtr.getSecond());
        
    }
}