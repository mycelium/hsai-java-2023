package utils;

import java.util.Objects;
import java.util.Optional;
import utils.hashUtils.*;
import utils.search.*;
public class MyHashMap implements MyHashInterface{

    MyHashElement[] hashTable;
    int size;

    MySearch searcher;

    public MyHashMap(int size, MySearch searchType){
        this.size = size;
        hashTable = new MyHashElement[size];
        searcher = searchType;
    }

    private void extend(){
        size *= 2;
        MyHashElement[] newHashTable = new MyHashElement[size];
        for (int i = 0; i<size/2; i++)
            if (Objects.nonNull(hashTable[i]))
                newHashTable[searcher.findPosToPut(hashTable[i].key(), newHashTable)] = hashTable[i];
        hashTable = newHashTable;
    };

    private void rehash(){
        MyHashElement[] newHashTable = new MyHashElement[size];
        for (int i = 0; i<size; i++)
            if (Objects.nonNull(hashTable[i]))
                newHashTable[searcher.findPosToPut(hashTable[i].key(), newHashTable)] = hashTable[i];
        hashTable = newHashTable;
    }

    public void printTable(){
        for (int i = 0; i<size; i++)
            if (Objects.nonNull(hashTable[i]))
            {
                System.out.print(i);
                System.out.print(": ");
                System.out.print("[");
                System.out.print(hashTable[i].key());
                System.out.print(", (");
                System.out.print(hashTable[i].value().x());
                System.out.print(", ");
                System.out.print(hashTable[i].value().y());
                System.out.print(")] ");
                System.out.println();
            }
    }

    @Override
    public void put(String key, MyPoint value) {
        int pos = searcher.findPosToPut(key, hashTable);
        if (pos!=-1) {
            MyHashElement newElement = new MyHashElement(key, value);
            hashTable[pos] = newElement;
        }
        else{
            while(pos==-1) {
                extend();
                pos = searcher.findPosToPut(key, hashTable);
            }
            hashTable[pos] = new MyHashElement(key, value);
        }
    }

    @Override
    public MyPoint get(String key) {
        int pos = searcher.search(key, hashTable);
        if (pos==-1)
            return null;
        return hashTable[pos].value();
    }

    @Override
    public MyPoint getOrElse(String key, MyPoint value) {
        int pos = searcher.search(key, hashTable);
        if (pos==-1)
            return value;
        return hashTable[pos].value();
    }

    @Override
    public Optional<MyPoint> getSafe(String key) {
        return Optional.ofNullable(get(key));
    }

    @Override
    public boolean contains(String key) {
        return getSafe(key).isPresent();
    }

    @Override
    public Optional<MyPoint> remove(String key) {
        int pos = searcher.search(key, hashTable);
        Optional<MyPoint> returnValue = Optional.empty();
        if (pos!=-1) {
            returnValue = Optional.of(hashTable[pos].value());
            hashTable[pos] = null;
        }
        rehash();
        return returnValue;
    }
}
