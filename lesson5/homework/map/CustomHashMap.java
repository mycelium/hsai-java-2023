package lesson5.homework.map;

import lesson5.homework.strategy.SearchStrategy;

import java.util.Objects;
import java.util.Optional;

public class CustomHashMap implements HashMapInterface {
    private HashMapElement[] table;
    private final SearchStrategy strategy;
    private int size;
    private int tableCapacity;

    public CustomHashMap(SearchStrategy strategy) {
        this.strategy = strategy;
        tableCapacity = 10;
        this.table = new HashMapElement[tableCapacity];
    }

    @Override
    public void put(String key, Point value) {
        if (this.size > Math.ceil((double) tableCapacity * 0.75)) {
            resizeTable();
        }
        if (contains(key)) {
            return;
        }
        HashMapElement newElement = new HashMapElement(key, value, Math.abs(key.hashCode()));
        int pos;
        boolean updateStep = true;
        pos = Math.abs(key.hashCode()) % table.length;
        while (Objects.nonNull(table[pos])) {
            pos += this.strategy.searchStep(updateStep);
            updateStep = false;
        }
        this.table[pos] = newElement;
        this.size++;
    }

    @Override
    public Point get(String key) {
        boolean updateStep = true;
        int pos = Math.abs(key.hashCode()) % table.length;
        while (Objects.nonNull(table[pos])) {
            if (Objects.equals(key, table[pos].getKey())) {
                return this.table[pos].getValue();
            }
            pos += this.strategy.searchStep(updateStep);
            updateStep = false;
        }
        return null;
    }

    @Override
    public Point getOrElse(String key, Point defaultValue) {
        var returnPoint = get(key);
        if (Objects.isNull(returnPoint)) {
            return defaultValue;
        }
        return returnPoint;
    }

    @Override
    public Optional<Point> getSafe(String key) {
        return Optional.ofNullable(get(key));
    }

    @Override
    public boolean contains(String key) {
        return getSafe(key).isPresent();
    }

    @Override
    public Optional<Point> remove(String key) {
        boolean notRemoved = true;
        boolean updateStep = true;
        if (contains(key)) {
            int pos = Math.abs(key.hashCode()) % tableCapacity;
            while (notRemoved) {
                if (Objects.equals(key, table[pos].getKey())) {
                    Point removePoint = table[pos].getValue();
                    table[pos] = null;
                    this.size--;
                    return Optional.of(removePoint);
                } else {
                    pos += this.strategy.searchStep(updateStep);
                    updateStep = false;
                }
            }
        }
        return Optional.empty();
    }

    private void resizeTable() {
        int oldCapacity = tableCapacity;
        int newCapacity = oldCapacity * 2;
        tableCapacity = newCapacity;
        HashMapElement[] copyTable = table;
        table = new HashMapElement[newCapacity];
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (Objects.nonNull(copyTable[i])) {
                this.put(copyTable[i].getKey(), copyTable[i].getValue());
            }
        }
        System.out.println("Table has been resized");
    }

    public void print() {
        for (int i = 0; i < tableCapacity; i++) {
            if (Objects.nonNull(this.table[i])) {
                System.out.print(this.table[i].getKey());
                System.out.print(" ");
                System.out.println(this.table[i].getValue());
            }
        }
    }
}
