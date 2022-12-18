package lesson5.homework.SearchStrategies;

import lesson5.homework.HashMap.CKHashMapNode;
import lesson5.homework.HashMap.Point;

public class QuadraticStrategy implements SearchStrategy {
    public Point IndexByKey(String key, CKHashMapNode[] array, int maxIdx) {
        int idx = Math.abs(key.hashCode() % maxIdx);
        int found = -1;
        int vacant = -1;
        int step = 1;
        while (idx < maxIdx && array[idx] != null && found == -1) {
            if (array[idx].key().equals(key)) found = idx;
            else {
                idx += Math.pow(step, 2);
                step++;
            }
        }
        if (found == -1) vacant = idx;
        return new Point(found, vacant);
    }
}
