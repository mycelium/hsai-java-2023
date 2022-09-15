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

    public abstract int[] Sort(int[] array);
}