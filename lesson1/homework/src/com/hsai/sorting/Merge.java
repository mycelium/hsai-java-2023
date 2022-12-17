package lesson1.homework.src.com.hsai.sorting;

public class Merge {
    public static void merge(int[] arr) {
        mergeRecurse(arr, 0, arr.length - 1);
    }

    private static void merge(int[] arr, int p, int q, int r) {

        int n1 = q - p + 1;
        int n2 = r - q;

        int[] L = new int[n1];
        int[] M = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[p + i];
        for (int j = 0; j < n2; j++)
            M[j] = arr[q + 1 + j];

        int i = 0;
        int j = 0;
        int k = p;

        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = M[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = M[j];
            j++;
            k++;
        }
    }

    private static void mergeRecurse(int[] array, int left, int right) {
        if (left < right) {

            int middle = (left + right) / 2;

            mergeRecurse(array, left, middle);
            mergeRecurse(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }

}
