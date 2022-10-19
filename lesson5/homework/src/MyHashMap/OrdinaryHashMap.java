package lesson5.homework.src.MyHashMap;

import lesson5.homework.src.Interfaces.OrdinaryInterface;

import java.util.Objects;
import java.util.Optional;

public class OrdinaryHashMap implements OrdinaryInterface {
    int step;
    HashElement[] hashTable;
    private final SearchTechniques searchTechnique;
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
                hashTable[hashValue] = new HashElement(k, val);
                break;
            }

            if (Objects.equals(hashTable[hashValue].getKey(), k)) {
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

        for (int i = 0; i < getTableSize(); i++) {
            if (Objects.nonNull(hashTable[hashValue])) {
                if (!hashTable[hashValue].isTombstone() && Objects.equals(k, hashTable[hashValue].getKey())) {
                    return hashTable[hashValue].getValue();
                }

                hashValue++;
                if (Objects.equals(hashValue, tableSize)) {
                    hashValue = 0;
                }
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
        return contains(k) ? get(k) : val;
    }

    @Override
    public boolean contains(String k) {
        return getSafe(k).isPresent();
    }

    @Override
    public Optional<Point> remove(String k) {
        if (!contains(k)) {
            return Optional.empty();
        } else {
            int hashValue = (k.hashCode() & Integer.MAX_VALUE) % tableSize;

            while (Objects.nonNull(hashTable[hashValue]) || Objects.nonNull(hashTable[hashValue]) && !hashTable[hashValue].isTombstone()) {
                if (Objects.equals(k, hashTable[hashValue].getKey())) {
                    Point returnPoint = hashTable[hashValue].getValue();
                    hashTable[hashValue].makeTombstone();
                    numberOfTombStones++;
                    numberOfElements--;
                    return Optional.of(returnPoint);
                }

                hashValue++;
                if (Objects.equals(hashValue, tableSize)) {
                    hashValue = 0;
                }
            }

            return Optional.ofNullable(get(k));
        }
    }

    private void resize(int newSize) {

        HashElement[] copyOfOldArray = hashTable;

        hashTable = new HashElement[newSize];

        tableSize = newSize;
        numberOfElements = 0;
        numberOfTombStones = 0;

        for (HashElement hashElement : copyOfOldArray) {
            if (Objects.nonNull(hashElement) && !hashElement.isTombstone()) {
                put(hashElement.getKey(), hashElement.getValue());
            }
        }

    }
}

