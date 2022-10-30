package lesson5.homework.src.HashTable;

import java.util.Objects;
import java.util.Optional;

public class HashTableTest {

    private final HashTable test;

    public HashTableTest(SearchTechniques techniques) {
        if (techniques == SearchTechniques.LINEAR) {
            test = HashTable.LinearBuilder.newInstance(SearchTechniques.LINEAR).setStep(31).build();
        } else if (techniques == SearchTechniques.QUADRATIC) {
            test = HashTable.OrdinaryBuilder.newInstance(SearchTechniques.QUADRATIC).build();
        } else {
            test = HashTable.OrdinaryBuilder.newInstance(SearchTechniques.ENUMERATE).build();
        }
    }

    public void tests() throws Exception {
        if (!checkFunctionsAfterRemoval()) {
            throw new Exception("checkFunctionsAfterRemoval() problem!");
        } else if (!getSimple()) {
            throw new Exception("getSimple() problem!");
        } else if (!getSafeSimple()) {
            throw new Exception("getSafeSimple() problem!");
        } else if (!getOrElseSimple()) {
            throw new Exception("getOrElseSimple() problem!");
        } else if (!containsOrNotContains()) {
            throw new Exception("containsOrNotContains() problem!");
        } else if (!remove()) {
            throw new Exception("remove() problem!");
        } else if (!removeAndResizeAndGet()) {
            throw new Exception("removeAndResize() problem!");
        }
    }

    boolean checkFunctionsAfterRemoval() {

        test.put("one", new Point(1, 1));
        test.put("three", new Point(2, 2));
        test.remove("one");
        if (test.get("one") != null) {
            return false;
        } else if (test.contains("one")) {
            return false;
        } else {
            return Objects.equals(test.getSafe("one"), Optional.empty()) && Objects.equals(test.getOrElse("one", new Point(10, 10)), new Point(10, 10));
        }
    }

    boolean getSimple() {

        test.put("one", new Point(1, 1));
        test.put("three", new Point(2, 2));
        if (Objects.isNull(test.get("one"))) {
            return false;
        } else if (Objects.nonNull(test.get("eleven"))) {
            return false;
        } else if (!Objects.equals(test.get("one"), test.get("one"))) {
            return false;
        } else {
            return !Objects.equals(test.get("one"), test.get("three"));
        }
    }

    boolean getSafeSimple() {

        test.put("one", new Point(1, 1));
        test.put("three", new Point(2, 2));
        if (Objects.isNull(test.getSafe("one"))) {
            return false;
        } else if (!Objects.equals(test.getSafe("eleven"), Optional.empty())) {
            return false;
        } else if (!Objects.equals(test.getSafe("one"), test.getSafe("one"))) {
            return false;
        } else {
            return !Objects.equals(test.getSafe("one"), test.getSafe("three"));
        }
    }

    boolean getOrElseSimple() {

        test.put("one", new Point(-1, 1));
        test.put("three", new Point(2, 2));
        test.put("low", new Point(-100, 100));
        Point elsePoint = new Point(10, 10);
        if (Objects.isNull(test.getOrElse("one", elsePoint))) {
            return false;
        } else if (!Objects.equals(test.getOrElse("eleven", elsePoint), elsePoint)) {
            return false;
        } else if (!Objects.equals(test.getOrElse("three", elsePoint), new Point(2, 2))) {
            return false;
        } else {
            return !Objects.equals(test.getOrElse("one", elsePoint), test.getOrElse("low", elsePoint));
        }
    }

    boolean containsOrNotContains() {
        test.put("one", new Point(-1, 1));
        if (!test.contains("one")) {
            return false;
        } else {
            return !test.contains("eleven");
        }
    }

    boolean remove() {
        String[] keys = new String[]{"one", "three"};
        test.put(keys[0], new Point(-1, 1));
        test.put(keys[1], new Point(2, 2));
        test.remove("one");
        test.remove("three");

        return Objects.equals(test.remove("one"), Optional.empty()) && Objects.equals(test.remove("love"), Optional.empty());
    }

    boolean removeAndResizeAndGet() {
        test.clear();
        String[] keys = new String[50];
        Point[] values = new Point[50];

        int i;
        for (i = 0; i < 50; ++i) {
            int number = i * i;
            keys[i] = Integer.toString(number);
            values[i] = new Point(number, number);
        }

        for (i = 0; i < 25; ++i) {
            test.put(keys[i], values[i]);
        }

        for (i = 0; i < 25; ++i) {
            if (!Objects.equals(test.remove(keys[i]), Optional.of(values[i]))) {
                return false;
            }
        }

        for (i = 0; i < 25; ++i) {
            if (Objects.nonNull(test.get(keys[i]))) {
                return false;
            }
        }

        for (i = 0; i < 50; ++i) {
            test.put(keys[i], values[i]);
        }

        test.put("one", new Point(1, 1));
        test.put("cat", new Point(3, 3));
        test.put("dog", new Point(9, 9));

        for (i = 0; i < 50; ++i) {
            if (Objects.isNull(test.get(keys[i]))) {
                return false;
            }
        }

        for (i = 0; i < 50; ++i) {
            if (!Objects.equals(test.remove(keys[i]), Optional.of(values[i]))) {
                return false;
            }
        }

        if (Objects.nonNull(test.get("oneOneONE"))) {
            return false;
        } else {
            return Objects.equals(test.get("dog"), new Point(9, 9));
        }
    }
}

