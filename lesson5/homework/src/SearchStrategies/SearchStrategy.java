package lesson5.homework.src.SearchStrategies;

import java.util.Objects;
import lesson5.homework.src.HashMap.HashMapElement;

public interface SearchStrategy {
    default void setStep(int step) {};
    int setHash(int hash);
    default int search(String key, HashMapElement[] table, int capacity) {
        int hash = Math.abs(key.hashCode());
        while (table[hash % capacity] != null) {
            if (Objects.equals(key, table[hash % capacity].getKey())) {
                    return hash % capacity;
            }
            hash = setHash(hash);
        }
        return -1;
    };
    default int findHash(int hash, HashMapElement[] table, int capacity) {
        while (Objects.nonNull(table[hash % capacity])) {
                hash = setHash(hash);
        }
        return hash;
    };
}