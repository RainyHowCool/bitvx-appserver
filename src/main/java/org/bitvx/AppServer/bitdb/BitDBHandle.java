package org.bitvx.AppServer.bitdb;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.bitvx.AppServer.utils.ByteUtils;

public class BitDBHandle {
    private Map<String, Integer> tables = new HashMap<String, Integer>();

    public BitDBHandle(InputStream stream) throws IOException, IllegalArgumentException {
        byte[] magicNumber = new byte[4]; 
        byte[] indexOffestRaw = new byte[4];
        byte[] dataAreaOffestRaw = new byte[4];
        int indexOffest;
        int dataAreaOffest;
        int bytesRead;

        byte[] magicNumberRight = new byte[4];
        magicNumberRight[0] = 'X';
        magicNumberRight[1] = 'K';
        magicNumberRight[2] = 'D';
        magicNumberRight[3] = 'B';

        byte[] lengthOfTableNameRaw = new byte[1];
        int lengthOfTableName;
        byte[] offestOfTableRaw = new byte[4];
        byte offestOfTable;

        if ((bytesRead = stream.read(magicNumber)) != 4 || magicNumber != magicNumberRight) {
            throw new IllegalArgumentException();
        }

        if ((bytesRead = stream.read(indexOffestRaw)) == 4) {
            indexOffest = ByteUtils.byteArrayToInt(indexOffestRaw);
        } else {
           throw new IllegalArgumentException();
        }

        if ((bytesRead = stream.read(dataAreaOffestRaw, indexOffest, 4)) == 4) {
            indexOffest = ByteUtils.byteArrayToInt(dataAreaOffestRaw);
        } else {
            throw new IllegalArgumentException();
        }

        if ((lengthOfTableName = stream.read(indexOffestRaw)) == -1) {
            throw new IllegalArgumentException();
        }

        if ((bytesRead = stream.read(offestOfTableRaw)) != 4) {
            throw new IllegalArgumentException();
        }

        offestOfTable = ByteUtils.byteArrayToInt(offestOfTableRaw);
    }
}