package com.hsai.algorithms;

public abstract class AbstractSort {
    protected String name;
    public AbstractSort() {
        this("");
    };
    protected AbstractSort(String arg) {
        name = arg;
    };
    protected void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    };
    public String getName() {
        return name;
    };
    public abstract void sort(int[] array);
}
