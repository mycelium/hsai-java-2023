package lesson1.homework.src.com.hsai.sort;

public class QuickSort {
    public static void quickSort(int[] array) {
        sortRecursion(array, 0, array.length - 1);
    }

    private static void sortRecursion(int[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);

            sortRecursion(array, begin, partitionIndex - 1);
            sortRecursion(array, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] array, int begin, int end) {
        int pivot = array[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        int tmp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = tmp;
        return i + 1;
    }
}

