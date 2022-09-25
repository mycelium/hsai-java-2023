package lesson1.homework.src.com.hsai.SortAlgs;

public class QuickSort {

    public static void quickSort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void sort(int[] array, int low, int high) {
        if (array.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        int base = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < base) {
                i++;
            }

            while (array[j] > base) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) {
            sort(array, low, j);
        }

        if (high > i) {
            sort(array, i, high);
        }
    }
}
