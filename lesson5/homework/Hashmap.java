package lesson5.homework;

import lesson5.homework.factories.CollisionSolver;

import java.util.Optional;

public class Hashmap {
    private final double loadFactor = 0.66d;
    private int capacity;
    private int size;
    private Bucket[] table;
    private final CollisionSolver collisionSolver;


    private record Bucket(String key, Point value) {
    }

    public Hashmap(int capacity, CollisionSolver solver) {
        this.capacity = capacity;
        this.collisionSolver = solver;
        this.table = new Bucket[capacity];
        this.size = 0;
    }


    private int hashOf(String key, int cap) {
        return key.hashCode() % cap;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Point get(String key) {
        var stg = collisionSolver.createStrategy();
        int idx = hashOf(key, capacity);
        int step = 0;
        while (table[idx] != null && step < capacity) {
            if (table[idx].key.equals(key)) {
                return table[idx].value;
            }
            idx = stg.nextIndex(idx, capacity);
            step++;
        }
        return null;
    }

    public Point getOrElse(String key, Point defaultVal) {
        Point elem = get(key);
        if (elem != null) {
            return elem;
        }
        return defaultVal;
    }

    public Optional<Point> getSafe(String key) {
        return Optional.ofNullable(get(key));
    }


    private void resize() {
        if ((double) size / capacity > loadFactor) {
            var stg = collisionSolver.createStrategy();
            int newCapacity = stg.getNewCapacity(capacity);
            Bucket[] copyTable = new Bucket[newCapacity];
            for (int i = 0; i < capacity; i++) {
                if (table[i] != null) {
                    copyTable[hashOf(table[i].key, newCapacity)] = table[i];
                }
            }
            capacity = newCapacity;
            table = copyTable;
        }
    }

    public void put(String key, Point point) {
        Bucket element = new Bucket(key, point);
        int idx = hashOf(key, capacity);
        var stg = collisionSolver.createStrategy();
        while (table[idx] != null && !table[idx].key.equals(key)) {
            idx = stg.nextIndex(idx, capacity);
        }
        table[idx] = element;
        size++;
        resize();
    }


    public Optional<Point> remove(String key) {
        if (!contains(key)) {
            return Optional.empty();
        }
        var stg = collisionSolver.createStrategy();
        int idx = hashOf(key, capacity);
        while (!table[idx].key.equals(key)) {
            idx = stg.nextIndex(idx, capacity);
        }
        var result = Optional.of(table[idx].value);
        table[idx] = null;
        int prevIdx = idx;
        idx = stg.nextIndex(idx, capacity);
        while (table[idx] != null && hashOf(table[idx].key, capacity) == hashOf(key, capacity)) {
            table[prevIdx] = table[idx];
            table[idx] = null;
            prevIdx = idx;
            idx = stg.nextIndex(idx, capacity);
        }
        return result;
    }
}
