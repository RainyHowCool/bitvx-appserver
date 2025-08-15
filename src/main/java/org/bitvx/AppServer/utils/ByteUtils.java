package org.bitvx.AppServer.utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ByteUtils {
    public static int byteArrayToInt(byte[] array) {
        return array[3] & 0xFF |
            (array[2] & 0xFF) << 8 |
            (array[1] & 0xFF) << 16 |
            (array[0] & 0xFF) << 24;
    }
    
    public static byte[] subByteArray(byte[] data, int start, int end) {
        return Arrays.copyOfRange(data, start, end);
    }

    public static int byteRegionToInt(byte[] data, int start, int end) {
        return ByteUtils.byteArrayToInt(subByteArray(data, start, end));
    }

    public static String byteRegionToString(byte[] data, int start, int end) {
        return new String(subByteArray(data, start, end), StandardCharsets.UTF_8);
    }
}