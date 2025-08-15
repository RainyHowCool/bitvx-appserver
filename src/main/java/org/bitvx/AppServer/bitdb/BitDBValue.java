package org.bitvx.AppServer.bitdb;

public class BitDBValue {
    private final BitDBType type;
    private final Object raw;

    public BitDBValue(BitDBType type, Object raw) {
        this.type = type;
        this.raw = raw;
    }

    public BitDBType getType() {
        return type;
    }

    public Object getRaw() {
        return raw;
    }
}