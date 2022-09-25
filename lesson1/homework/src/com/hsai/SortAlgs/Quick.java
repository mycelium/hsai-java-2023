package lesson1.homework.src.com.hsai.SortAlgs;

public class Quick {
    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (arr.length != 0 || low < high) {
            int mid = low + (high - low) / 2;
            int opora  = arr[mid];
            int i = low;
            int j = high;
            while (i <= j) {
                while (arr[i] < opora) {
                    i++;
                }
                while (arr[j] > opora) {
                    j--;
                }
                if (i <= j) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                    i++;
                    j--;
                }
            }
            if (low < j) {
                quickSort(arr, low, j);
            }
            if (high > i) {
                quickSort(arr, i, high);
            }
        }
    };
}