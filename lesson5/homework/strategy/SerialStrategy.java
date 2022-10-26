package lesson5.homework.strategy;

import lesson5.homework.map.HashMapElement;

import java.util.Objects;

public class SerialStrategy implements SearchStrategy {
    public int search(String key, HashMapElement[] table) {
        int pos = Math.abs(key.hashCode()) % table.length;
        while (Objects.nonNull(table[pos])) {
            if (Objects.equals(key, table[pos].getKey())) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    public int putPos(int hash, HashMapElement[] table) {
        int pos = hash % table.length;
        while (Objects.nonNull(table[pos])) {
            pos++;
        }
        return pos;
    }
}