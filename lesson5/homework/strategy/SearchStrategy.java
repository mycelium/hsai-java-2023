package lesson5.homework.strategy;

import lesson5.homework.map.HashMapElement;

public interface SearchStrategy {
    int search(String key, HashMapElement[] table);

    int putPos(int hash, HashMapElement[] table);
}
