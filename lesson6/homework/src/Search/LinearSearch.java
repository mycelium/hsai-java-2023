package lesson6.homework.src.Search;

import lesson6.homework.src.Helpers.HashMapElement;

public class LinearSearch extends AbstractSearch {

    public int search(String key, HashMapElement[] table, int capacity) {
        int hash = Math.abs(key.hashCode());
        while (table[hash % capacity] != null) {
            if (table[hash % capacity].getKey().equals(key)) {
                return hash % capacity;
            }
            hash += this.step;
        }
        return -1;
    }

    public int indexForPutting(int hash, HashMapElement[] table, int capacity) {
        while (table[hash % capacity] != null) {
            hash += this.step;
        }
        return hash;
    }
}
