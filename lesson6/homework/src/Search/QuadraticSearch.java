package lesson6.homework.src.Search;

import lesson6.homework.src.Helpers.HashMapElement;

public class QuadraticSearch extends AbstractSearch {

    public int search(String key, HashMapElement[] table, int capacity) {
        int hash = Math.abs(key.hashCode());
        int q = 1;
        while (table[hash % capacity] != null) {
            if (table[hash % capacity].getKey().equals(key)) {
                return hash % capacity;
            }
            hash += (q++) ^ 2;
        }
        return -1;
    }

    public int indexForPutting(int hash, HashMapElement[] table, int capacity) {
        int q = 1;
        while (table[hash % capacity] != null) {
            hash += (q++) ^ 2;
        }
        return hash;
    }
}
