package lesson5.homework.strategy;

import lesson5.homework.map.HashMapElement;

import java.util.Objects;

public class LinearStrategy implements SearchStrategy {
    final int step;

    public LinearStrategy(int step) {
        this.step = step;
    }

    @Override
    public int search(String key, HashMapElement[] table) {
        int pos = Math.abs(key.hashCode()) % table.length;
        while (Objects.nonNull(table[pos])) {
            if (Objects.equals(key, table[pos].getKey())) {
                return pos;
            }
            pos += step;
        }
        return -1;
    }

    @Override
    public int putPos(int hash, HashMapElement[] table) {
        int pos = hash % table.length;
        while (Objects.nonNull(table[pos])) {
            pos += this.step;
        }
        return pos;
    }
}