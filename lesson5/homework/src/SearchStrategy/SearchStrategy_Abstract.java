package lesson5.homework.src.SearchStrategy;

import lesson5.homework.src.HashMap.HashMap_Element;

public abstract class SearchStrategy_Abstract {
    int step = 0;

    public void setStep(int step) {
        this.step = step;
    }

    public int search(String key, HashMap_Element[] table, int capacity) {
        if (this instanceof QuadraticSearch) {
            QuadraticSearch.reset();
        }

        int hash = Math.abs(key.hashCode());
        while (table[hash % capacity] != null) {
            if (table[hash % capacity].getKey().equals(key) && table[hash % capacity] != HashMap_Element.tombStone) {
                return hash % capacity;
            }
            hash = newHash(hash);
        }

        return -1;
    }

    public int indexForPutting(int hash, HashMap_Element[] table, int capacity) {
        if (this instanceof QuadraticSearch) {
            QuadraticSearch.reset();
        }

        while (table[hash % capacity] != null && table[hash % capacity] != HashMap_Element.tombStone) {
            hash = newHash(hash);
        }

        return hash;
    }

    abstract protected int newHash(int hash);
}
