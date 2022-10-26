package lesson5.homework.src.HashTable;

import java.util.Objects;

public class LinearHashMap extends OrdinaryHashMap {
    public LinearHashMap() {
        super();
    }

    @Override
    public Point get(String k) {
        int hashValue = (k.hashCode() & Integer.MAX_VALUE) % getTableSize();

        if (Objects.nonNull(hashTable[hashValue])) {

            for (int i = 0; i < getTableSize(); ++i) {
                if (Objects.nonNull(hashTable[hashValue]) && hashTable[hashValue] != getTombStone() && hashTable[hashValue].key().equals(k)) {
                    return hashTable[hashValue].value();
                }

                hashValue = (hashValue + getStep()) % getTableSize();
            }
        }

        return null;
    }
}
