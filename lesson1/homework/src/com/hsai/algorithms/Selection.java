package com.hsai.algorithms;

public class Selection extends AbstractSort{
    public Selection() {
        super("Selection sort"); 
    };
    public void sort(int[] array) {
        for (int left = 0; left < array.length; left++) {
            int minInd = left;
            for (int i = left; i < array.length; i++) {
                if (array[i] < array[minInd]) {
                    minInd = i;
                }
            }
            if (minInd != left){
                swap(array, left, minInd);
            }
        }
    };
}
