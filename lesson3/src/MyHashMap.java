import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class MyHashMap implements OrdinaryInterface, LinearInterface {
    static final double maxLoadFactor = 0.75;
    static int step;
    HashElement[] hashTable;
    private SearchTechniques searchTechnique;
    private int numberOfElements;
    private int numberOfTombStones;
    private int tableSize;

    public MyHashMap() {

        searchTechnique = SearchTechniques.ENUMERATE;

        tableSize = 7;

        numberOfElements = 0;

        numberOfTombStones = 0;

        step = 1;

        hashTable = new HashElement[tableSize];

        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = null;
        }

    }

    public int GCDForSteps(int a, int b) {
        return b == 0 ? a : GCDForSteps(b, a % b);
    }

    public int getTableSize() {
        return tableSize;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int getNumberOfTombStones() {
        return numberOfTombStones;
    }

    public SearchTechniques getSearchTechnique() {
        return searchTechnique;
    }

    public void setSearchTechnique(SearchTechniques technique) {
        this.searchTechnique = technique;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int s) throws IllegalArgumentException {
        if (GCDForSteps(tableSize, s) == 1) {
            step = s;
        } else throw new IllegalArgumentException("Better use a number which greatest common divisor is 1.");
    }

    public void put(@NotNull String k, Point val) {

        if ((double) (numberOfElements + numberOfTombStones) / (double) tableSize > maxLoadFactor) {
            resize(tableSize * 2 + 1);
        }

        int hashValue = (k.hashCode() & 0x7fffffff) % tableSize;

        for (; hashValue < tableSize; hashValue++) {
            if (Objects.isNull(hashTable[hashValue])) {
                hashTable[hashValue] = new HashElement(k, val);
                break;
            } else if (Objects.equals(hashTable[hashValue].getKey(), k)) return;

            if (Objects.equals(hashValue, tableSize - 1)) {
                hashValue = 0;
            }
        }
        numberOfElements++;
    }

    public Point get(@NotNull String k) {

        if (Objects.equals(searchTechnique, SearchTechniques.LINEAR)) {
            return getLinear(k);
        } else if (Objects.equals(searchTechnique, SearchTechniques.QUADRATIC)) {
            return getQuadratic(k);
        }

        int hashValue = (k.hashCode() & 0x7fffffff) % tableSize;

        while (Objects.nonNull(hashTable[hashValue])) {
            if (Objects.equals(k, hashTable[hashValue].getKey())) {
                return hashTable[hashValue].getValue();
            } else {
                hashValue++;
                if (Objects.equals(hashValue, tableSize)) hashValue = 0;
            }
        }
        return null;
    }

    private Point getLinear(String k) {

        int hashValue = (k.hashCode() & 0x7fffffff) % tableSize;

        for (int i = 0; i < tableSize; i++) {

            if (Objects.nonNull(hashTable[hashValue]) && hashTable[hashValue].getKey().equals(k))
                return hashTable[hashValue].getValue();

            hashValue = hashValue + getStep();
            if (hashValue > tableSize - 1) hashValue %= tableSize;
        }
        return null;
    }

    private Point getQuadratic(String k) {

        int hashValue = (k.hashCode() & 0x7fffffff) % tableSize;

        for (int j = 1; j < tableSize; j++) {

            if (Objects.nonNull(hashTable[hashValue]) && Objects.equals(k, hashTable[hashValue].getKey()))
                return hashTable[hashValue].getValue();

            hashValue = ((k.hashCode() & 0x7fffffff) + (int) Math.pow(2, j)) % tableSize;
        }

        return null;
    }

    public Optional<Point> getSafe(@NotNull String k) {
        return Optional.ofNullable(get(k));
    }

    public Point getOrElse(@NotNull String k, Point val) {
        return contains(k) ? get(k) : val;
    }

    public boolean contains(@NotNull String k) {
        return getSafe(k).isPresent();
    }

    public Optional<Point> remove(@NotNull String k) {

        if (getSafe(k).isEmpty()) return Optional.empty();

        int hashValue = (k.hashCode() & 0x7fffffff) % tableSize;

        while (Objects.nonNull(hashTable[hashValue]) && !hashTable[hashValue].isTombstone()) {
            if (Objects.equals(k, hashTable[hashValue].getKey())) {
                Point returnPoint = hashTable[hashValue].getValue();
                hashTable[hashValue].makeTombstone();
                numberOfTombStones++;
                numberOfElements--;
                return Optional.of(returnPoint);
            } else {
                hashValue++;
                if (Objects.equals(hashValue, tableSize))
                    hashValue = 0;
            }
        }
        return Optional.ofNullable(get(k));
    }

    private void resize(int newSize) {

        HashElement[] newArray = new HashElement[newSize];

        for (int i = 0; i < tableSize; i++) {
            if (Objects.nonNull(hashTable[i]) && !hashTable[i].isTombstone()) {
                int hashValue = (hashTable[i].getKey().hashCode() & 0x7fffffff) % newSize;
                newArray[hashValue] = hashTable[i];
            }
        }
        numberOfTombStones = 0;
        hashTable = newArray;
        tableSize = newSize;
    }

    static class HashElement {

        String key;

        Point value;

        public HashElement(String key, Point value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Point getValue() {
            return value;
        }

        public void makeTombstone() {
            this.key = "DELETED";
            this.value = null;
        }

        public boolean isTombstone() {
            return key.equals("DELETED") && value == null;
        }
    }

}
