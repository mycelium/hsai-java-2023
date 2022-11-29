package lesson5.homework.SearchStrategies;

import lesson5.homework.HashMap.CKHashMapNode;
import lesson5.homework.HashMap.Point;

public interface SearchStrategy {
    Point IndexByKey(String key, CKHashMapNode[] array, int maxIdx);
}
