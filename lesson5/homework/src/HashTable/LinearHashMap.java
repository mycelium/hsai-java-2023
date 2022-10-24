package lesson5.homework.src.HashTable;

import lesson5.homework.src.Interfaces.LinearInterface;

import java.util.Objects;

public class LinearHashMap extends OrdinaryHashMap implements LinearInterface {
    public LinearHashMap(SearchTechniques searchTechnique) {
        super(searchTechnique);
    }

    @Override
    public void setStep(int s) throws IllegalArgumentException {
        if (!Objects.equals(gcdForSteps(getTableSize(), s), 1)) {
            throw new IllegalArgumentException();
        } else {
            this.step = s;
        }
    }

    @Override
    public Point get(String k) {
        int hashValue = (k.hashCode() & Integer.MAX_VALUE) % getTableSize();

        if (Objects.nonNull(hashTable[hashValue])) {

            for (int i = 0; i < getTableSize(); ++i) {
                if (Objects.nonNull(hashTable[hashValue]) && hashTable[hashValue]!=tombStone && hashTable[hashValue].key().equals(k)) {
                    return hashTable[hashValue].value();
                }

                hashValue = (hashValue + getStep()) % getTableSize();
            }
        }

        return null;
    }
}
