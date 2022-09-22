package lesson1.homework.src.com.hsai.sortclasses;

import java.util.Arrays;

// https://www.geeksforgeeks.org/merge-sort/
public class MergeSort extends AbstractSort {
    public MergeSort() {
        super("Merge sort");
    }

    private void merge(int[] array, int low, int mid, int high) {
        int[] left = Arrays.copyOfRange(array, low, mid);
        int[] right = Arrays.copyOfRange(array, mid + 1, high);

        int i = 0;
        int j = 0;
        int k = low;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            array[k] = right[j];
            j++;
            k++;
        }
    }

    private void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;

            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);

            merge(array, low, mid, high);
        }
    }

    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }
}
