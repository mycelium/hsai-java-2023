package lesson5.homework.src.MyHashMap;

import java.util.Objects;

public class QuadraticHashMap extends OrdinaryHashMap {
    public QuadraticHashMap(SearchTechniques searchTechnique) {
        super(searchTechnique);
    }

    @Override
    public Point get(String k) {
        int hashValue = (k.hashCode() & Integer.MAX_VALUE) % getTableSize();

        if (Objects.nonNull(hashTable[hashValue])) {
            for (int j = 1; j < getTableSize(); ++j) {
                if (Objects.nonNull(hashTable[hashValue]) && !hashTable[hashValue].isTombstone() && Objects.equals(k, hashTable[hashValue].key())) {
                    return hashTable[hashValue].value();
                }

                int newHashValue = (hashValue + (j * j + j) / 2) % roundUp2(getTableSize());
                if (newHashValue < getTableSize()) {
                    hashValue = newHashValue;
                }
            }
        }
        return null;
    }

    private int roundUp2(int v) {
        return (int) Math.pow(2.0, Math.ceil(Math.log(v) / Math.log(2.0)));
    }
}
