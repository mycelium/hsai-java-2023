package lesson6.homework.src.Search;

import lesson6.homework.src.Helpers.HashMapElement;

public abstract class AbstractSearch{
    int step = 1;

    public void setStep(int step) {
        this.step = step;
    }
    abstract public int search(String key, HashMapElement[] table, int capacity);

    abstract public int indexForPutting(int hash,  HashMapElement [] table, int capacity);
}
