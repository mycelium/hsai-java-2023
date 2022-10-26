package lesson5.homework.map;

import lesson5.homework.strategy.SearchStrategy;

import java.util.Objects;
import java.util.Optional;

public class CustomHashMap implements HashMapInterface {
    HashMapElement[] table;
    SearchStrategy strategy;
    int size;
    int tableCapacity;

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
        HashMapElement newElement = new HashMapElement(key, value);
        int pos;
        pos = strategy.putPos(newElement.getHash(), table);
        this.table[pos] = newElement;
        this.size++;
    }

    @Override
    public Point get(String key) {
        int pos = this.strategy.search(key, this.table);
        if (pos == -1) {
            return null;
        }
        return this.table[pos].getValue();
    }

    @Override
    public Point getOrElse(String key, Point value) {
        var returnPoint = get(key);
        if (Objects.isNull(returnPoint)) {
            return value;
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
        if (contains(key)) {
            int pos = Math.abs(key.hashCode()) % tableCapacity;
            if (Objects.equals(key, table[pos].getKey())) {
                Point removePoint = table[pos].getValue();
                table[pos] = null;
                this.size--;
                return Optional.of(removePoint);
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
                this.put(copyTable[i].key, copyTable[i].value);
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
