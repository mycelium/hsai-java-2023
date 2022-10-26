package lesson6.homework.src.Search;

import lesson6.homework.src.Helpers.HashMapElement;

public abstract class AbstractSearch {
    int step = 1;

    public void setStep(int step) {
        this.step = step;
    }

    abstract protected int changedHash(int hash);

    public int search(String key, HashMapElement[] table, int capacity) {
        if (this instanceof QuadraticSearch) {
            QuadraticSearch.reset();
        }
        int hash = Math.abs(key.hashCode());
        while (table[hash % capacity] != null) {
            if (table[hash % capacity].getKey().equals(key) && table[hash % capacity] != HashMapElement.TOMBSTONE) {
                return hash % capacity;
            }
            hash = changedHash(hash);
        }
        return -1;
    }

    public int indexForPutting(int hash, HashMapElement[] table, int capacity) {
        if (this instanceof QuadraticSearch) {
            QuadraticSearch.reset();
        }
        while (table[hash % capacity] != null && table[hash % capacity] != HashMapElement.TOMBSTONE) {
            hash = changedHash(hash);
        }
        return hash;
    }
}
