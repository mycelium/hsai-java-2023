package lesson1.homework.src.com.hsai.sort;

public class QuickSort {

    public static void sort(int[] array) {
        sortWithParameters(array, 0, array.length - 1);
    }

    private static void sortWithParameters(int[] array, int begin, int end) {
        if (begin < end) {
            int pivotIndex = partition(array, begin, end);
            sortWithParameters(array, begin, pivotIndex - 1);
            sortWithParameters(array, pivotIndex + 1, end);
        }
    }

    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static int partition(int[] array, int begin, int end) {
        int pivot = array[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, end);
        return i + 1;
    }
}
