package lesson5.homework.Hashmap;


import lesson5.homework.search.SequentialSearch;

import java.awt.*;
import java.util.Optional;
public class Hashmap implements InterfaceHashmap{
    private final double loadFactor = 0.75;
    private int capacity;
    private int size;

    private final double threshold;
    private Bucket[] table;
    private final SequentialSearch collisionSolver;

    private record Bucket(String key, Point value) {
    }

    public Hashmap(int capacity, SequentialSearch solver) {
        this.capacity = capacity;
        this.collisionSolver = solver;
        this.table = new Bucket[capacity];
        this.size = 0;
        this.threshold = this.capacity * loadFactor;
    }


    private int hashOf(String key, int capacity) {
        return key.hashCode() % capacity;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Point get(String key) {
        var seqSear = collisionSolver.createSearch();
        int ind = hashOf(key, capacity);
        int step = 0;
        while (table[ind] != null && step < capacity) {
            if (table[ind].key.equals(key)) {
                return table[ind].value;
            }
            ind = seqSear.nextIndex(ind, capacity);
            step++;
        }
        return null;
    }

    public Point getOrElse(String key, Point defVal) {
        Point element = get(key);
        if (element != null) {
            return element;
        }
        return defVal;
    }

    public Optional<Point> getSafe(String key) {
        return Optional.ofNullable(get(key));
    }


    private void resize() {
        if ((double) size > threshold) {
            var seqSear = collisionSolver.createSearch();
            int newCapacity = seqSear.getNewCapacity(capacity);
            Bucket[] tmpTable = new Bucket[newCapacity];
            for (int i = 0; i < capacity; i++) {
                if (table[i] != null) {
                    tmpTable[hashOf(table[i].key, newCapacity)] = table[i];
                }
            }
            capacity = newCapacity;
            table = tmpTable;
        }
    }

    public void put(String key, Point point) {
        Bucket element = new Bucket(key, point);
        int ind = hashOf(key, capacity);
        var seqSear = collisionSolver.createSearch();
        while (table[ind] != null && !table[ind].key.equals(key)) {
            ind = seqSear.nextIndex(ind, capacity);
        }
        table[ind] = element;
        size++;
        resize();
    }


    public Optional<Point> remove(String key) {
        if (!contains(key)) {
            return Optional.empty();
        }
        var seqSear = collisionSolver.createSearch();
        int ind = hashOf(key, capacity);
        while (!table[ind].key.equals(key)) {
            ind = seqSear.nextIndex(ind, capacity);
        }
        var answ = Optional.of(table[ind].value);
        table[ind] = null;
        int prevInd = ind;
        ind = seqSear.nextIndex(ind, capacity);
        while (table[ind] != null && hashOf(table[ind].key, capacity) == hashOf(key, capacity)) {
            table[prevInd] = table[ind];
            table[ind] = null;
            prevInd = ind;
            ind = seqSear.nextIndex(ind, capacity);
        }
        return answ;
    }
}
