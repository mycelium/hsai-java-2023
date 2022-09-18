package lesson1.homework.src.com.hsai.SortingMethods;

public class SelectionSort {

    public static void selectionSort(int[] array) {

        int size = array.length;

        for (int step = 0; step < size - 1; step++) {

            int min_idx = step;

            for (int i = step + 1; i < size; i++) {

                if (array[i] < array[min_idx]) {
                    min_idx = i;
                }
            }
            int temp = array[step];
            array[step] = array[min_idx];
            array[min_idx] = temp;
        }
    }
}
