package lesson6.homework.src.HashMap;

import lesson6.homework.src.Helpers.*;

import java.util.Optional;

import lesson6.homework.src.Search.*;



public class HashMap {
    HashMapElement[] table;
    double loadFactor;
    int capacity;
    int size;
    AbstractSearch searchStrategy;

    public HashMap() {
        this.loadFactor = 0.75;
        this.capacity = 6;
        this.table = new HashMapElement[capacity];
        this.searchStrategy = new ConsistentSearch();
        this.size = 0;
    }

    public HashMap(int capacity) {
        this.loadFactor = 0.75;
        this.capacity = capacity;
        this.table = new HashMapElement[capacity];
        this.searchStrategy = new ConsistentSearch();
        this.size = 0;
    }

    public void setCapacity(int capacity) {
        if (capacity > size) {
            this.capacity = capacity;
        }
    }

    public void setConsistentStrategy() {
        if (this.size == 0) {
            this.searchStrategy = new ConsistentSearch();
        }
    }

    public void setLinearStrategy() {
        if (this.size == 0) {
            this.searchStrategy = new LinearSearch();
        }
    }

    public void setQuadraticStrategy() {
        if (this.size == 0) {
            this.searchStrategy = new QuadraticSearch();
        }
    }

    public void setStep(int step) {
        this.searchStrategy.setStep(step);
    }

    private void resizeIfTableIsFull(int newCapacity) {
        if ((double) this.size / this.capacity > this.loadFactor) {
            HashMapElement[] copyTable = new HashMapElement[newCapacity];
            for (int i = 0; i < this.capacity; i++) {
                if (this.table[i] != null) {
                    copyTable[this.table[i].getHashCode() % newCapacity] = this.table[i];
                }
            }
            this.capacity = newCapacity;
            this.table = copyTable;
        }
    }

    public void put(String s, Point p) {
        if (this.contains(s)) {
            return;
        }
        HashMapElement el = new HashMapElement(s, p);
        int hash;
        hash = searchStrategy.indexForPutting(el.getHashCode(), this.table, this.capacity);
        el.setHashCode(hash);
        this.table[hash % this.capacity] = el;
        this.size++;
        this.resizeIfTableIsFull(2 * this.capacity);
    }
    public Point get(String key) {
        int index = this.searchStrategy.search(key,this.table,this.capacity);
        if (index == -1) {
            return null;
        }
        return this.table[index].getValue();
    }

    public Optional<Point> getSafe(String key) {
        int index = this.searchStrategy.search(key,this.table,this.capacity);
        if (index == -1) {
            return Optional.empty();
        }
        return Optional.of(this.table[index].getValue());
    }

    public Point getOrElse(String key, Point def) {
        int index = this.searchStrategy.search(key,this.table,this.capacity);
        if (index == -1) {
            return def;
        }
        return this.table[index].getValue();
    }

    public Optional<Point> remove(String key) {
        int index = this.searchStrategy.search(key,this.table,this.capacity);
        if (index == -1) {
            return Optional.empty();
        }
        Point removed = this.table[index].getValue();
        this.table[index] = null;
        return Optional.of(removed);
    }

    public boolean contains(String key) {
        return searchStrategy.search(key,table,capacity) != -1;
    }

}
