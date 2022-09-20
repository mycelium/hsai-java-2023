package com.hsai.sortalgs;

public abstract class AbstractSort {
    protected String name;

    public AbstractSort() {
        this("");
    }

    public AbstractSort(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public abstract void sort(int[] array);
}
