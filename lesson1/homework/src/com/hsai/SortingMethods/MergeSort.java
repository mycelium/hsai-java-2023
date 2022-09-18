package lesson1.homework.src.com.hsai.SortingMethods;

public class MergeSort {
    public static void mergeSort(int[] array){
        mergeRecurse(array,0,array.length-1);
    }

    private static void merge(int[] array, int p, int q, int r) {

        int n1 = q - p + 1;
        int n2 = r - q;

        int[] L = new int[n1];
        int[] M = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = array[p + i];
        for (int j = 0; j < n2; j++)
            M[j] = array[q + 1 + j];

        int i, j, k;
        i = 0;
        j = 0;
        k = p;

        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = M[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = M[j];
            j++;
            k++;
        }
    }

    private static void mergeRecurse(int[] array, int left, int right){
        if (left < right) {

            int middle = (left + right) / 2;

            mergeRecurse(array, left, middle);
            mergeRecurse(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }
}