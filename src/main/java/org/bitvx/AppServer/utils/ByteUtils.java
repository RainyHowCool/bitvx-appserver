package org.bitvx.AppServer.utils;

public class ByteUtils {
    public static int byteArrayToInt(byte[] array) {
        return array[3] & 0xFF |
            (array[2] & 0xFF) << 8 |
            (array[1] & 0xFF) << 16 |
            (array[0] & 0xFF) << 24;
    }
}