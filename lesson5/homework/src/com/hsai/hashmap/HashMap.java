package com.hsai.hashmap;

import java.util.Arrays;
import java.util.Optional;

import com.hsai.point.*;
import com.hsai.hashmap.exceptions.*;

public class HashMap implements HashMapInterface {
    private static final float LOAD_FACTOR = 0.75f;
    private static final int CAPACITY_DEFAULT = 8;
    private int capacity = CAPACITY_DEFAULT;
    private int size = 0;
    private Node[] table;
    private StrategyHandler handler;

    private record Node(String key, Point value) {
        private static Node nil = new Node(null, null);

        @Override
        public String toString() {
            if (key != null && value != null) {
                return key + ": " + value.toString();
            } else {
                return "nil";
            }
        }
    }

    // Strategy handlers:
    private abstract class StrategyHandler {
        protected abstract int findIndexOf(String key);

        protected abstract int findIndexToPut(String key);

        public Point findWithStrategy(String key) {
            int index = findIndexOf(key);

            if (index < capacity) {
                return table[index].value();
            } else {
                return null;
            }
        }

        public void putWithStrategy(String key, Point value) {

        }
    }

    private class SequentialHandler extends StrategyHandler {
        protected int findIndexOf(String key) {
            int index = getHash(key);
            while (index < capacity && (table[index] == Node.nil || !(table[index].key().equals(key)))) {
                index++;
            }
            return index;
        }

        protected int findIndexToPut(String key) {
            int index = getHash(key);
            while (table[index] != Node.nil) {
                index++;
                if (!(index < capacity)) {
                    doubleCapacityAndRehash();
                    index = getHash(key);
                }
            }
            return index;
        }
    }

    private class LinearHandler extends StrategyHandler {
        private int step = 0;

        protected int findIndexOf(String key) {
            int index = getHash(key);
            while (index < capacity && (table[index] == Node.nil || !(table[index].key().equals(key)))) {
                index += step;
            }
            return index;
        }

        protected int findIndexToPut(String key) {
            int index = getHash(key);
            while (table[index] != Node.nil) {
                index += step;
                if (!(index < capacity)) {
                    doubleCapacityAndRehash();
                    index = getHash(key);
                }
            }
            return index;
        }

        public void setStep(int step) throws IncorrectStepException {
            if (step > 0 && step < capacity) {
                this.step = step;
            } else {
                throw new IncorrectStepException(step, capacity);
            }
        }
    }

    private class QuadraticHandler extends StrategyHandler {
        protected int findIndexOf(String key) {
            int index = getHash(key);
            int argument = 1;
            while (index < capacity && (table[index] == Node.nil || !(table[index].key().equals(key)))) {
                index += argument * argument;
                argument++;
            }
            return index;
        }

        protected int findIndexToPut(String key) {
            int index = getHash(key);
            int argument = 1;
            while (table[index] != Node.nil) {
                index += argument * argument;
                argument++;
                if (!(index < capacity)) {
                    doubleCapacityAndRehash();
                    index = getHash(key);
                    argument = 1;
                }
            }
            return index;
        }
    }

    // !Strategy handlers

    // Builder class and build method:
    public class Builder {

        public class SequentialBuilder extends Builder {
        }

        public class LinearBuilder extends Builder {
            public Builder setStep(int step) throws IncorrectStepException {
                ((LinearHandler) handler).setStep(step);
                return LinearBuilder.this;
            }
        }

        public class QuadraticBuilder extends Builder {
        }

        public SequentialBuilder setSequentialStrategy() {
            handler = new SequentialHandler();
            return new SequentialBuilder();
        }

        public LinearBuilder setLinearStrategy() {
            handler = new LinearHandler();
            return new LinearBuilder();
        }

        public QuadraticBuilder setQuadraticStrategy() {
            handler = new QuadraticHandler();
            return new QuadraticBuilder();
        }

        public HashMap build() {
            return HashMap.this;
        }
    }

    public static Builder builder() {
        return new HashMap().new Builder();
    }

    // !Builder class and build method

    // init blocks and constructors:
    {
        table = new Node[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = Node.nil;
        }
    }

    private HashMap() {
    }
    // !init blocks and constructors

    public void put(String key, Point value) {
        int index = handler.findIndexOf(key);
        // if node has the same key but different value, then replace val with new one
        // if key AND value are the same, do not put
        if (index < capacity) {
            if (!table[index].value().equals(value)) {
                table[index] = new Node(key, value);
            }
            return;
        }

        if (!(size < capacity * LOAD_FACTOR)) {
            doubleCapacityAndRehash();
        }

        index = getHash(key);
        while (table[index] != Node.nil){
            index = handler.findIndexToPut(key);
            if (!(index < capacity)) {
                doubleCapacityAndRehash();
                index = getHash(key);
            }
        }

        table[index] = new Node(key, value);
        size++;
    }

    public Point get(String key) {
        return handler.findWithStrategy(key);
    }


    public Point getOrElse(String key, Point defaultValue) {
        Point point = get(key);
        if (point != null) {
            return point;
        } else {
            return defaultValue;
        }
    }

    public Optional<Point> getSafe(String key) {
        return Optional.ofNullable(get(key));
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Optional<Point> remove(String key) {
        int index = handler.findIndexOf(key);
        Optional<Point> option = Optional.empty();
        if (index < capacity) {
            option = Optional.of(table[index].value());
            table[index] = Node.nil;
            size--;
        }
        return option;
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }

    private int getHash(String key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private void doubleCapacityAndRehash() {
        capacity *= 2;
        rehash();
    }

    private void rehash() {
        Node[] tableCopy = Arrays.copyOf(table, table.length);
        table = new Node[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = Node.nil;
        }
        size = 0;

        for (int i = 0; i < tableCopy.length; i++) {
            if (tableCopy[i] != Node.nil) {
                put(tableCopy[i].key(), tableCopy[i].value());
            }
        }
    }
}
