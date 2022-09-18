package lesson1.homework.src.com.hsai.SortingMethods;

public class InsertionSort {
    public static void insertionSort(int[] array){

        int size = array.length;
        for (int step = 1; step < size; step++) {
            int key = array[step];
            int j = step - 1;

            while (j >= 0 && key < array[j]) {
                array[j + 1] = array[j];
                --j;
            }
            array[j + 1] = key;
        }

    }
}
