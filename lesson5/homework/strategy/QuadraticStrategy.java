package lesson5.homework.strategy;

import lesson5.homework.map.HashMapElement;

import java.util.Objects;

public class QuadraticStrategy implements SearchStrategy {
    int q = 1;

    @Override
    public int search(String key, HashMapElement[] table) {
        int pos = Math.abs(key.hashCode()) % table.length;
        q = 1;
        while (Objects.nonNull(table[pos])) {
            if (Objects.equals(key, table[pos].getKey())) {
                return pos;
            }
            pos += (int) Math.pow(q++, 2);
        }
        return -1;
    }

    @Override
    public int putPos(int hash, HashMapElement[] table) {
        q = 1;
        int pos = hash % table.length;
        while (Objects.nonNull(table[pos])) {
            pos += (int) Math.pow(q++, 2);
        }
        return pos;
    }
}
