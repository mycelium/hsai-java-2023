package lesson5.homework.src.HashMap;

import lesson5.homework.src.SearchStrategy.SearchStrategy_Abstract;
import lesson5.homework.src.SearchStrategy.LinearSearch;
import lesson5.homework.src.SearchStrategy.QuadraticSearch;

import java.util.Optional;

public class HashMap implements HashMap_Interface {
    private HashMap_Element[] table;
    private int tableSize;
    private int tableCapacity;
    private double loadFactor = 0.75;
    private SearchStrategy_Abstract searchStrategy;

    public HashMap() {
    }

    @Override
    public void put(String str, Point value) {
        if (this.contains(str)) {
            return;
        }
        HashMap_Element elem = new HashMap_Element(str, value);

        int hash;
        hash = searchStrategy.indexForPutting(elem.getHashCode(), this.table, this.tableCapacity);

        this.table[hash % this.tableCapacity] = elem;
        this.tableSize++;
        this.resizeTable(this.tableCapacity + 10);
    }

    @Override
    public Point get(String key) {
        int index = this.searchStrategy.search(key, this.table, this.tableCapacity);

        if (index == -1) {
            return null;
        }

        return this.table[index].getValue();
    }

    @Override
    public Point getOrElse(String key, Point defaultVal) {
        int index = this.searchStrategy.search(key, this.table, this.tableCapacity);

        if (index == -1) {
            return defaultVal;
        }

        return this.table[index].getValue();
    }

    @Override
    public Optional<Point> getSafe(String key) {
        return Optional.ofNullable(this.get(key));
    }

    @Override
    public boolean contains(String key) {
        return searchStrategy.search(key, table, tableCapacity) != -1;
    }

    @Override
    public Optional<Point> remove(String key) {
        int index = this.searchStrategy.search(key, this.table, this.tableCapacity);

        if (index == -1) {
            return Optional.empty();
        }

        Point removed = this.table[index].getValue();
        this.table[index] = HashMap_Element.tombStone;
        this.tableSize--;

        return Optional.ofNullable(removed);
    }
    private void resizeTable(int newCapacity) {
        if (((double) this.tableSize / this.tableCapacity) > this.loadFactor) {
            HashMap_Element[] tableCopy = new HashMap_Element[newCapacity];
            HashMap_Element[] tablePrev = this.table;

            int prevCapacity = this.tableCapacity;
            this.tableCapacity = newCapacity;
            this.table = tableCopy;

            for (int i = 0; i < prevCapacity; i++) {
                if (tablePrev[i] != null) {
                    this.put(tablePrev[i].getKey(), tablePrev[i].getValue());
                    this.tableSize--;
                }
            }
        }
    }

    public static LinearBuilder linearBuilder() {
        return new HashMap().new LinearBuilder();
    }

    public static QuadraticBuilder quadraticBuilder() {
        return new HashMap().new QuadraticBuilder();
    }

    abstract public class Builder {
        private Builder() {
            HashMap.this.loadFactor = 0.75;
            HashMap.this.tableCapacity = 10;
        }

        public HashMap build() {
            HashMap.this.tableSize = 0;
            HashMap.this.table = new HashMap_Element[HashMap.this.tableCapacity];
            return HashMap.this;
        }

        abstract public Builder setCapacity(int capacity);

        abstract public Builder setLoadFactor(int loadFactor);
    }

    public class LinearBuilder extends Builder {
        private LinearBuilder() {
            super();
            HashMap.this.searchStrategy = new LinearSearch();
        }

        public LinearBuilder setCapacity(int capacity) {
            HashMap.this.tableCapacity = capacity;
            return this;
        }

        public LinearBuilder setLoadFactor(int loadFactor) {
            HashMap.this.loadFactor = loadFactor;
            return this;
        }

        public LinearBuilder setStep(int step) {
            HashMap.this.searchStrategy.setStep(step);
            return this;
        }
    }

    public class QuadraticBuilder extends Builder {
        private QuadraticBuilder() {
            super();
            HashMap.this.searchStrategy = new QuadraticSearch();
        }

        public QuadraticBuilder setCapacity(int capacity) {
            HashMap.this.tableCapacity = capacity;
            return this;
        }

        public QuadraticBuilder setLoadFactor(int loadFactor) {
            HashMap.this.loadFactor = loadFactor;
            return this;
        }
    }
}
