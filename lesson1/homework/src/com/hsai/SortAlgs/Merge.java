package lesson1.homework.src.com.hsai.SortAlgs;

public class Merge {
    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    };

    private static void merge(int[] arr, int left, int mid, int right) {
        int l = mid - left + 1;
        int r = right - mid;
        int lArr[] = new int [l];
        int rArr[] = new int [r];
        System.arraycopy(arr, left, lArr, 0, l);
        System.arraycopy(arr, mid + 1, rArr, 0, r);
        int i = 0;
        int j = 0;
        int k = left;
        while (i < l && j < r) {
            if (lArr[i] <= rArr[j]) {
                arr[k++] = lArr[i++];
            } 
            else {
                arr[k++] = rArr[j++];
            }
        }
        while (i < l) {
            arr[k++] = lArr[i++];
        }
        while (j < r) {
            arr[k++] = rArr[j++];
        }
    };

    private static void mergeSort(int[] arr, int left, int right) {
        if (right > left) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    };
}