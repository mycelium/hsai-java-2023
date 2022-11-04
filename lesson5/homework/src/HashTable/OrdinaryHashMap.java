package lesson5.homework.src.HashTable;

import lesson5.homework.src.Interfaces.OrdinaryInterface;

import java.util.Objects;
import java.util.Optional;

public class OrdinaryHashMap implements OrdinaryInterface {
    private static final HashElement tombStone = new HashElement(null, "DELETED", null);

    private final int minCapacity = 7;
    protected HashElement[] hashTable;
    private int numberOfElements;
    private int numberOfTombStones;
    private int tableSize;
    private int step;

    public OrdinaryHashMap() {
        tableSize = minCapacity;
        numberOfElements = 0;
        numberOfTombStones = 0;
        hashTable = new HashElement[tableSize];
    }

    public int gcdForSteps(int a, int b) {
        return b == 0 ? a : gcdForSteps(b, a % b);
    }

    public int getTableSize() {
        return tableSize;
    }

    public HashElement getTombStone() {
        return tombStone;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int s) throws IllegalArgumentException {
        if (!Objects.equals(gcdForSteps(getTableSize(), s), 1)) {
            throw new IllegalArgumentException();
        } else {
            step = s;
        }
    }

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

    public Point get(String k) {
        int hashValue = (k.hashCode() & Integer.MAX_VALUE) % tableSize;

        while (Objects.nonNull(hashTable[hashValue])) {
            if (hashTable[hashValue] != tombStone && Objects.equals(k, hashTable[hashValue].key())) {
                return hashTable[hashValue].value();
            }
            hashValue++;
            if (Objects.equals(hashValue, tableSize)) {
                hashValue = 0;
            }
        }
        return null;
    }

    public Optional<Point> getSafe(String k) {
        return Optional.ofNullable(get(k));
    }

    public Point getOrElse(String k, Point val) {
        var returnPoint = get(k);
        return returnPoint == null ? val : returnPoint;
    }

    public boolean contains(String k) {
        return getSafe(k).isPresent();
    }

    public Optional<Point> remove(String k) {
        if (contains(k)) {
            int hashValue = (k.hashCode() & Integer.MAX_VALUE) % tableSize;

            while (Objects.nonNull(hashTable[hashValue]) || Objects.nonNull(hashTable[hashValue]) && hashTable[hashValue] != tombStone) {
                if (Objects.equals(k, hashTable[hashValue].key())) {
                    Point returnPoint = hashTable[hashValue].value();
                    hashTable[hashValue] = tombStone;
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
            if (Objects.nonNull(hashElement) && hashElement != tombStone) {
                put(hashElement.key(), hashElement.value());
            }
        }

    }

    public void clear() {
        hashTable = new HashElement[minCapacity];

        tableSize = minCapacity;
        numberOfElements = 0;
        numberOfTombStones = 0;
    }

    record HashElement(String key, String stone, Point value) {
    }
}
