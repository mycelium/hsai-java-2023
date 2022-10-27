package lesson5.homework.src.SearchStrategies;

import java.util.Objects;
import lesson5.homework.src.HashMap.HashMapElement;

public class QuadraticSearch implements SearchStrategy{
    int stepQ = 1;

    public int setHash(int hash) {
        hash += Math.pow(stepQ++, 2);
        return hash;
    }

    @Override
    public int search(String key, HashMapElement[] table, int capacity) {
        stepQ = 1;
        int hash = Math.abs(key.hashCode());
        while (table[hash % capacity] != null) {
            if (Objects.equals(key, table[hash % capacity].getKey())) {
                    return hash % capacity;
            }
            hash = setHash(hash);
        }
        return -1;
    }

    @Override
    public int findHash(int hash, HashMapElement[] table, int capacity) {
        stepQ = 1;
        while (Objects.nonNull(table[hash % capacity])) {
                hash = setHash(hash);
        }
        return hash;
    }
}
    
    