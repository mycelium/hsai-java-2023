package com.hsai.sortalgs;

public class QuickSort extends AbstractSort {
    public QuickSort() {
        super("Quick sort");
    }

    // https://otus.ru/nest/post/788/
    private void quickSort(int[] array, int low, int high) {
        if ((array.length == 0) || (low >= high)) {
            return;
        }

        int mid = low + (high - low) / 2;
        int pivot = array[mid];

        int i = low;
        int j = high;
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
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
    }

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }
}
