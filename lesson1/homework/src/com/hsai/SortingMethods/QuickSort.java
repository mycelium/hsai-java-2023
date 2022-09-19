package lesson1.homework.src.com.hsai.SortingMethods;

public class QuickSort {
    public static void quickSort(int[] array) {
        quickRecurse(array, 0, array.length - 1);
    }

    private static int partition(int[] array, int low, int high) {

        int pivot = array[high];

        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {

                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return (i + 1);
    }

    private static void quickRecurse(int[] array, int low, int high) {
        if (low < high) {

            int pi = partition(array, low, high);

            quickRecurse(array, low, pi - 1);

            quickRecurse(array, pi + 1, high);
        }

    }
}