package org.bitvx.AppServer.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AdvInputStream extends BufferedInputStream {
    public static final int LAST = -1;
    public static final int INT_SIZE = 4;

    public AdvInputStream(InputStream in) {
        super(in);
    }

    public byte[] readToByteArray(int off, int len) throws IOException, IllegalArgumentException {
        byte[] buffer = new byte[len];

        if (off == LAST) {
            if (this.read(buffer) == 4) {
                return buffer;
            } else {
            throw new IllegalArgumentException();
            }
        } else {
            if (this.read(buffer, off, len) == len) {
                return buffer;
            } else {
            throw new IllegalArgumentException();
            }
        }
    }

    public int readToInt(int off) throws IOException, IllegalArgumentException {
        return ByteUtils.byteArrayToInt(readToByteArray(off, INT_SIZE));
    }

    public String readToString(int off, int len) throws IOException, IllegalArgumentException {
        return new String(readToByteArray(off, len), StandardCharsets.UTF_8);
    }
}