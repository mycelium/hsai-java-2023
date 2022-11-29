package com.hsai.algorithms;

public class Quick extends AbstractSort{
    public Quick() {
        super("Quick sort"); 
    };
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    };
    private void quickSort(int[] array, int low, int high) {
        if (array.length == 0){
            return;
        }
        if (low >= high){
            return;
        }

        int mid = low + (high - low) / 2;
        int opora  = array[mid];

        int i = low;
        int j = high;
        while (i <= j) {
            while (array[i] < opora ) {
                i++;
            }
            while (array[j] > opora ) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(array, low, j);
        }
        if (high > i) {
            quickSort(array, i, high);
        }
    };
}
