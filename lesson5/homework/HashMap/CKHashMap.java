package lesson5.homework.HashMap;

import lesson5.homework.SearchStrategies.*;

import java.util.Optional;

public class CKHashMap {
    static {
        CKHashMapNode nullNode;
        nullNode = new CKHashMapNode(" ", new Point(0, 0));
    }

    final private SearchStrategy strategy;
    private CKHashMapNode[] map;
    private int capacity;

    public CKHashMap(StrategyNames name, int step) throws IllegalArgumentException {
        capacity = 1;
        map = new CKHashMapNode[capacity];
        if (step < 1) throw new IllegalArgumentException("Negative step value: " + step);
        strategy = switch (name) {
            case SEQUENTIAL:
                yield new SequentialStrategy();
            case LINEAR:
                yield new LinearStrategy(step);
            case QUADRATIC:
                yield new QuadraticStrategy();
            default:
                throw new IllegalArgumentException("Wrong search strategy type: " + name);
        };
    }

    public CKHashMap(StrategyNames name) throws IllegalArgumentException {
        this(name, 1);
    }

    public void put(String key, Point value) {
        int vacant;
        do {
            Point index = strategy.IndexByKey(key, map, capacity);
            vacant = index.y();
            if (vacant == -1) return;
            if (vacant >= capacity) {
                ReHash(vacant + 1);
                vacant = strategy.IndexByKey(key, map, capacity).y();
            }
        } while (vacant >= capacity);
        map[vacant] = new CKHashMapNode(key, value);
    }

    private void ReHash(int newCapacity) {
        CKHashMapNode[] newMap;
        boolean isEnough;
        do {
            isEnough = true;
            int i = 0;
            int overCapacity = newCapacity;
            newMap = new CKHashMapNode[newCapacity];
            for (i = 0; i < capacity; i++) {
                int vacant = -1;
                if (map[i] != null) {
                    vacant = strategy.IndexByKey(map[i].key(), newMap, newCapacity).y();
                    if (vacant >= newCapacity) {
                        overCapacity = vacant + 1;
                        isEnough = false;
                    } else newMap[vacant] = new CKHashMapNode(map[i].key(), map[i].value());
                }
            }
            newCapacity = overCapacity;
        } while (!isEnough);

        map = newMap;
        capacity = newCapacity;
    }

    public boolean contains(String key) {
        return strategy.IndexByKey(key, map, capacity).x() != -1;
    }

    public Point get(String key) {
        if (contains(key)) return map[strategy.IndexByKey(key, map, capacity).x()].value();
        else return null;
    }

    public Point getOrElse(String key, Point defvalue) {
        if (contains(key)) return map[strategy.IndexByKey(key, map, capacity).x()].value();
        else return defvalue;
    }

    public Optional<Point> getSafe(String key) {
        return Optional.ofNullable(get(key));
    }

    public Optional<Point> remove(String key) {
        if (!contains(key)) {
            return Optional.empty();
        } else {
            int pos = strategy.IndexByKey(key, map, capacity).x();
            Point out = map[pos].value();
            map[pos] = null;
            ReHash(capacity);
            return Optional.of(out);
        }
    }

    public void PrintMap() {
        System.out.println("HashMap of capacity " + capacity + ":");
        for (int i = 0; i < capacity; i++) {
            if (map[i] != null)
                System.out.println(i + ": " + map[i].key() + " - " + map[i].value().x() + ", " + map[i].value().y());
        }
    }
}
