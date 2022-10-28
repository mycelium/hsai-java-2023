package utils.search;

import utils.hashUtils.MyHashElement;

import java.util.Objects;

public abstract class MySearch {

    public int search(String key, MyHashElement[] hashTable){
        int pos = Math.abs(key.hashCode())%hashTable.length;
        int step = 0;
        while(Objects.nonNull(hashTable[pos])){
            if (Objects.equals(key, hashTable[pos].key()))
                return pos;
            pos = nextIndex(pos, ++step);
        }
        return -1;
    };

    public int findPosToPut(String key, MyHashElement[] hashTable){
        int pos = Math.abs(key.hashCode())%hashTable.length;
        int step = 0;
        do{
            if (Objects.isNull(hashTable[pos])){
                return pos;
            }
            pos = nextIndex(pos, ++step);
        } while (pos<hashTable.length);

        return -1;
    };
    public abstract int nextIndex(int initialIndex, int step);


}
