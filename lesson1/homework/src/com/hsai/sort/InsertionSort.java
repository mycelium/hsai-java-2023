package lesson1.homework.src.com.hsai.sort;

public class InsertionSort {
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int data = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > data) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = data;
        }
    }
}
