package com.hsai.algorithms;

public class Insertion extends AbstractSort{
    public Insertion() {
        super("Insertion sort"); // in C++ : Insertion() : AbstractSort("Insertion sort");
    };
    public void sort(int[] array) {
        for (int left = 0; left < array.length; left++) {
            int value = array[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    break;
                }
            }
            array[i + 1] = value;
        }
    };
}
