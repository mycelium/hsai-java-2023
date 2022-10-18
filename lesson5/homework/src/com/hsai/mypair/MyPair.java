package com.hsai.mypair;

public record MyPair<K, V>(K key, V val) {
    @Override
    public String toString() {
        return key.toString() + ": " + val.toString();
    }
}
