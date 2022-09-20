package lesson1.homework.src.com.hsai.sort;

public class SelectionSort {

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minimalIndex = i;

            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minimalIndex])
                    minimalIndex = j;
            }

            if (minimalIndex != i) {
                int temp = array[i];
                array[i] = array[minimalIndex];
                array[minimalIndex] = temp;
            }
        }
    }
}
