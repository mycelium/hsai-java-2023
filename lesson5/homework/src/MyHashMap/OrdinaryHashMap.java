package lesson5.homework.src.MyHashMap;

import lesson5.homework.src.Interfaces.OrdinaryInterface;

import java.util.Objects;
import java.util.Optional;

public class OrdinaryHashMap implements OrdinaryInterface {
    protected record HashElement(String key, String stone, Point value) {
        public boolean isTombstone() {
            return stone.equals("DELETED") && value == null && key == null;
        }
    }
    private final SearchTechniques searchTechnique;
    int step;
    HashElement[] hashTable;
    private int numberOfElements;
    private int numberOfTombStones;
    private int tableSize;

    public OrdinaryHashMap(SearchTechniques searchTechniques) {
        searchTechnique = searchTechniques;
        tableSize = 7;
        numberOfElements = 0;
        numberOfTombStones = 0;
        step = 1;
        hashTable = new HashElement[tableSize];
    }

    public int gcdForSteps(int a, int b) {
        return b == 0 ? a : gcdForSteps(b, a % b);
    }

    public int getTableSize() {
        return tableSize;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int getStep() {
        return step;
    }

    @Override
    public void put(String k, Point val) {
        if ((double) (numberOfElements + numberOfTombStones) / (double) tableSize > 0.75) {
            resize(tableSize * 2 + 1);
        }

        for (int hashValue = (k.hashCode() & Integer.MAX_VALUE) % tableSize; hashValue < tableSize; ++hashValue) {
            if (Objects.isNull(hashTable[hashValue])) {
                hashTable[hashValue] = new HashElement(k, "INSERTED", val);
                break;
            }

            if (Objects.equals(hashTable[hashValue].key(), k)) {
                return;
            }

            if (Objects.equals(hashValue, tableSize - 1)) {
                hashValue = 0;
            }
        }

        numberOfElements++;
    }

    @Override
    public Point get(String k) {
        int hashValue = (k.hashCode() & Integer.MAX_VALUE) % tableSize;

        while (Objects.nonNull(hashTable[hashValue])) {
            if (!hashTable[hashValue].isTombstone() && Objects.equals(k, hashTable[hashValue].key())) {
                return hashTable[hashValue].value();
            }
            hashValue++;
            if (Objects.equals(hashValue, tableSize)) {
                hashValue = 0;
            }
        }
        return null;
    }

    @Override
    public Optional<Point> getSafe(String k) {
        return Optional.ofNullable(get(k));
    }

    @Override
    public Point getOrElse(String k, Point val) {
        var returnPoint = get(k);
        return returnPoint == null ? val : returnPoint;
    }

    @Override
    public boolean contains(String k) {
        return getSafe(k).isPresent();
    }

    @Override
    public Optional<Point> remove(String k) {
        if (contains(k)) {
            int hashValue = (k.hashCode() & Integer.MAX_VALUE) % tableSize;

            while (Objects.nonNull(hashTable[hashValue]) || Objects.nonNull(hashTable[hashValue]) && !hashTable[hashValue].isTombstone()) {
                if (Objects.equals(k, hashTable[hashValue].key())) {
                    Point returnPoint = hashTable[hashValue].value();
                    hashTable[hashValue] = new HashElement(null, "DELETED", null);
                    numberOfTombStones++;
                    numberOfElements--;
                    return Optional.of(returnPoint);
                }

                hashValue++;
                if (Objects.equals(hashValue, tableSize)) {
                    hashValue = 0;
                }
            }
        }
        return Optional.empty();
    }

    private void resize(int newSize) {

        HashElement[] copyOfOldArray = hashTable;

        hashTable = new HashElement[newSize];

        tableSize = newSize;
        numberOfElements = 0;
        numberOfTombStones = 0;

        for (HashElement hashElement : copyOfOldArray) {
            if (Objects.nonNull(hashElement) && !hashElement.isTombstone()) {
                put(hashElement.key(), hashElement.value());
            }
        }

    }
}

