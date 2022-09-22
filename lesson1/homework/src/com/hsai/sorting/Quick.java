package lesson1.homework.src.com.hsai.sorting;

public class Quick {
    public static void quick(int[] arr) {
        quickRecurse(arr, 0, arr.length - 1);
    }

    private static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];

        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {

                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

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
