package lesson1.homework.src.com.hsai.sort;

public class InsertionSort {

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int keyElement = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > keyElement) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = keyElement;
        }
    }
}
