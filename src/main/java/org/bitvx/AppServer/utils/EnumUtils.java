package org.bitvx.AppServer.utils;

import java.io.IOException;

public class EnumUtils {
    public static <T extends Enum<T>> T getEnumFromIndex(Class<T> type, int index) throws IOException {
        T[] obj = type.getEnumConstants();

        for (T it : obj) {
            if (it.ordinal() == index) {
                return it;
            }
        }
        throw new IOException("Cannot found vaild enum!");
    }
}