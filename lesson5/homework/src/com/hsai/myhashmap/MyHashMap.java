package com.hsai.myhashmap;

import java.util.Arrays;
import java.util.Optional;

import com.hsai.myhashmap.interfaces.*;
import com.hsai.myhashmap.LookupStrategy;
import com.hsai.mypoint.*;
import com.hsai.myhashmap.exceptions.*;

public class MyHashMap implements HashMapInterface {
    private final float LOAD_FACTOR = 0.75f;
    private final int CAPACITY_DEFAULT = 16;
    private int capacity;
    private int size = 0;
    private StrategyHandler strategyHandler;
    private StrategyHandler previousHandler;
    private Node[] table;

    abstract class StrategyHandler {
        abstract int getNextIndex(int index);
    }

    class SequentialHandler extends StrategyHandler {
        SequentialHandler() {
            if (previousHandler != this && size > 0) {
                rehash();
            }
            previousHandler = this;
        }

        int getNextIndex(int index) {
            return ++index;
        }
    }

    class QuadraticHandler extends StrategyHandler {
        QuadraticHandler() {
            if (previousHandler != this && size > 0) {
                rehash();
            }
            previousHandler = this;
        }

        int getNextIndex(int index) {
            return index * 2;
        }
    }

    class LinearHandler extends StrategyHandler {
        int step;

        LinearHandler() {
            previousHandler = this;
        }

        void setStep(int step) {
            this.step = step;
            rehash();
        }

        int getNextIndex(int index) {
            return index + step;
        }
    }

    protected record Node(String key, MyPoint val) {
        @Override
        public String toString() {
            return key + ": " + val.toString();
        }
    }

    {
        capacity = CAPACITY_DEFAULT;
        table = new Node[capacity];
    }

    public MyHashMap(LookupStrategy strategy) throws IncorrectStrategyException {
        setStrategy(strategy);
    }

    public void put(String key, MyPoint val) {
        if (isCapped()) {
            increaseCapacityAndRehash();
        }

        int index = getHash(key);
        while (table[index] != null) {
            // if node has the same key and different val, then replace val with new one
            // if key AND val are same, do not insert
            if (table[index].key.equals(key)) {
                if (!table[index].val.equals(val)) {
                    break;
                } else {
                    return;
                }
            }

            index = strategyHandler.getNextIndex(index);
            if (index >= capacity) {
                increaseCapacityAndRehash();
                index = getHash(key);
            }
        }

        table[index] = new Node(key, val);
        size++;
    }

    public MyPoint get(String key) {
        int index = findIndex(key);

        if (index < capacity) {
            return table[index].val;
        } else {
            return null;
        }
    }

    public MyPoint getOrElse(String key, MyPoint val) {
        MyPoint found = get(key);

        if (found != null) {
            return found;
        } else {
            return val;
        }
    }

    public Optional<MyPoint> getSafe(String key) {
        MyPoint found = get(key);
        Optional<MyPoint> opt = Optional.ofNullable(found);
        return opt;
    }

    public boolean contains(String key) {
        MyPoint found = get(key);

        if (found != null) {
            return true;
        } else {
            return false;
        }
    }

    public Optional<MyPoint> remove(String key) {
        int index = findIndex(key);

        Optional<MyPoint> opt = Optional.empty();
        if (index < capacity) {
            opt = Optional.of(table[index].val);
            table[index] = null;
            size--;
        }

        return opt;
    }

    public void setStrategy(LookupStrategy strategy) throws IncorrectStrategyException {
        switch (strategy) {
            case SEQUENTIAL:
                strategyHandler = new SequentialHandler();
                return;
            case QUADRATIC:
                strategyHandler = new QuadraticHandler();
                return;
            case LINEAR:
                strategyHandler = new LinearHandler();
                return;
            default:
                throw new IncorrectStrategyException();
        }
    }

    public void setStep(int step) throws IncorrectStepException {
        if (step > 0 && step < capacity) {
            // This provokes run-time exception if set strategy is not LINEAR,
            // couldn't come up with implementation which raises compile error.
            if (((LinearHandler) strategyHandler).step != step) {
                ((LinearHandler) strategyHandler).step = step;
                if (size > 0) {
                    rehash();
                }
            }
        } else {
            throw new IncorrectStepException(step, capacity);
        }
    }

    public void clear() {
        capacity = CAPACITY_DEFAULT;
        table = new Node[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        if (size > 0) {
            return Arrays.toString(table);
        } else {
            return "Map is empty!";
        }
    }

    private boolean isCapped() {
        return !(size < (capacity * LOAD_FACTOR));
    }

    private void increaseCapacityAndRehash() {
        capacity *= 2;
        rehash();
    }

    private void rehash() {
        Node[] tmp = Arrays.copyOf(table, capacity);
        table = new Node[capacity];
        size = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] != null) {
                put(tmp[i].key, tmp[i].val);
            }
        }
    }

    private int getHash(String key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private int findIndex(String key) {
        int index = getHash(key);
        while (index < capacity && (table[index] == null || !(table[index].key.equals(key)))) {
            index = strategyHandler.getNextIndex(index);
        }
        return index;
    }
}
