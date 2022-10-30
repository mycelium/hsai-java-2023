package com.hsai.myhashtab;

import java.beans.IndexedPropertyChangeEvent;
import java.util.Arrays;
import java.util.Optional;

import com.hsai.myhashtab.exceptions.*;
import com.hsai.myhashtab.strategies.*;
import com.hsai.mypoint.*;

public class MyHashTab {
    private final float LOAD_FACTOR = 0.75f;
    private int size;
    private int capacity;
    private Strategy stategy;
    private int step = 0;
    private int quad = 0;
    private Node[] table;

    {
        capacity = 16;
        table = new Node[capacity];
    }

    record Node(String key, MyPoint val) {
        @Override
        public String toString() {
            return key + ": " + val.toString();
        }
    }

    public MyHashTab(int step, Strategy strat) throws IncorrectStepException {
        setStep(step);
        setStrategy(strat);
    }
    public void PrintHashTab() throws IncorrectStepException, IncorrectStrategyException {
        int i = 0;
        System.out.println("Hash table with capacity " + capacity);
        while(i < capacity ) {
            if(table[i] != null) {
                System.out.println("[" + i + "]" + table[i].toString());
            }
            i++;
        }
    }
    public void put(String key, MyPoint val) throws IncorrectStepException, IncorrectStrategyException {
        if (isCapped()) {
            incCapAndRehash();
        }
        int index = getHash(key);
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                if (table[index].val.equals(val)) {
                    return;
                } else {
                    break;
                }
            }
            index = findIndexForPut(key);
            if (index >= capacity) {
                incCapAndRehash();
                index = getHash(key);
            }
        }
        table[index] = new Node(key, val);
        size++;
    }

    public Optional<MyPoint> remove(String key) throws IncorrectStepException, IncorrectStrategyException {
        int index = findIndex(key);
        Optional<MyPoint> omp = Optional.empty();

        if (index < capacity) {
            omp = Optional.of(table[index].val);
            table[index] = null;
            size--;
        }
        return omp;
    }

    private void incCapAndRehash() throws IncorrectStepException, IncorrectStrategyException {
        Node[] tmp = Arrays.copyOf(table, capacity);
        capacity *= 2;
        table = new Node[capacity];
        size = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] != null) {
                put(tmp[i].key, tmp[i].val);
            }
        }
    }

    private int next(int index) throws IncorrectStepException, IncorrectStrategyException {
        switch (stategy) {
            case SEQUENT:
                return ++index;
            case LIN:
                if (step > 0 && step < capacity) {
                    return index + step;
                } else {
                    throw new IncorrectStepException(capacity, step);
                }
            case QUADR:
                quad++;
                return (int) (index + Math.pow(quad, 2));
            default:
                throw new IncorrectStrategyException();
        }
    }

    public MyPoint get(String key) throws IncorrectStepException, IncorrectStrategyException {
        if (size != 0) {
            int index = findIndex(key);
            if (index < capacity) {
                return table[index].val;
            } else {
                return null;
            }
        }
        return null;
    }

    public MyPoint getOrElse(String key, MyPoint val) throws IncorrectStepException, IncorrectStrategyException {
        MyPoint found = get(key);
        if (found != null) {
            return found;
        } else {
            return val;
        }
    }

    public Optional<MyPoint> getSafe(String key) throws IncorrectStepException, IncorrectStrategyException {
        MyPoint found = get(key);
        Optional<MyPoint> o = Optional.ofNullable(found);
        return o;
    }

    public boolean contains(String key) throws IncorrectStepException, IncorrectStrategyException {
        MyPoint found = get(key);
        if (found != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCapped() {
        return size >= (capacity * LOAD_FACTOR);
    }

    private int getHash(String key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private int findIndex(String key) throws IncorrectStepException, IncorrectStrategyException {
        int index = getHash(key);
        while (index < capacity && (table[index] == null || !(table[index].key.equals(key)))) {
            index = next(index);
        }
        return index;
    }

    private int findIndexForPut(String key) throws IncorrectStepException, IncorrectStrategyException {
        int index = getHash(key);
        while (index < capacity && table[index] != null) {            
            index = next(index);
        }
        return index;
    }

    private void setStrategy(Strategy s) {
        stategy = s;
    }

    private void setStep(int s) throws IncorrectStepException {
        if(s > capacity){
            throw new IncorrectStepException(capacity, s);
        }
        step = s;
    }
}
