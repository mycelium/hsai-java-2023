package lesson5.homework.src.HashMap;

import java.util.Optional;
import java.util.Objects;
import lesson5.homework.src.HashMap.*;
import lesson5.homework.src.SearchStrategies.*;


public class MyHashMap {
    private HashMapElement[] table;
    private int capacity;
    private int size;
    private SearchStrategy strategy;

    private MyHashMap() {}

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public class Builder {
        private Builder() {
            MyHashMap.this.capacity = 4;
        }

        public MyHashMap build() {
            MyHashMap.this.size = 0;
            MyHashMap.this.table = new HashMapElement[MyHashMap.this.capacity];
            return MyHashMap.this;
        }
    }

    public static ConsistentBuilder consistentBuilder() {
        return new MyHashMap().new ConsistentBuilder();
    }
    public class ConsistentBuilder extends Builder {
        private ConsistentBuilder() {
            super();
            MyHashMap.this.strategy = new ConsistentSearch();
        }
    }

    public static LinearBuilder linearBuilder() {
        return new MyHashMap().new LinearBuilder();
    }
    public class LinearBuilder extends Builder {
        private LinearBuilder() {
            super();
            MyHashMap.this.strategy = new LinearSearch();
        }
        public LinearBuilder setStep(int step) {
            MyHashMap.this.strategy.setStep(step);
            return this;
        }
    }
    
    public static QuadraticBuilder quadraticBuilder() {
        return new MyHashMap().new QuadraticBuilder();
    }
    public class QuadraticBuilder extends Builder {
        private QuadraticBuilder() {
            super();
            MyHashMap.this.strategy = new QuadraticSearch();
        }
    }

    private void extendTable() {
        int newCapacity = this.capacity * 2;
        int oldCapacity = this.capacity;
        this.capacity = newCapacity;
        HashMapElement[] newTable = new HashMapElement[newCapacity];
        HashMapElement[] oldTable = this.table;
        this.table = newTable;
        this.size = 0;
        
        for (int i = 0; i < oldCapacity; i++) {
            if (oldTable[i] != null) {
                this.put(oldTable[i].getKey(), oldTable[i].getValue());
            }
        }
    }

    public void put(String key, Point value) {
        if (this.contains(key)) return;
        HashMapElement newElement = new HashMapElement(key, value);
        int hash = strategy.findHash(newElement.getHash(), this.table, this.capacity);
        this.table[hash % this.capacity] = newElement;
        this.size++;
        if ((double)this.size / this.capacity > 0.75)
            this.extendTable();
    }

    public Point get(String key) {
        int position = this.strategy.search(key, this.table, this.capacity);
        if (position == -1) return null;
        return this.table[position].getValue();
    }

    public Point getOrElse(String key, Point value) {
        int position = this.strategy.search(key, this.table, this.capacity);
        if (position == -1) return value;
        return this.table[position].getValue();
    }

    public Optional<Point> getSafe(String key) {
        return Optional.ofNullable(this.get(key));
    }
    
    public boolean contains(String key) {
        return strategy.search(key, table, capacity) != -1;
    }

    public Optional<Point> remove(String key) {
        int position = this.strategy.search(key, this.table, this.capacity);
        if (position == -1) return Optional.empty();
        Point removedPoint = this.table[position].getValue();
        this.table[position] = null;
        this.size--;
        return Optional.ofNullable(removedPoint);
    }
}