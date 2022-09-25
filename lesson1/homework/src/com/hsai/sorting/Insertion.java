package lesson1.homework.src.com.hsai.sorting;
public class Insertion {
    public static void insert(int[] arr) {

        int size = arr.length;
        for (int step = 1; step < size; step++) {
            int key = arr[step];
            int j = step - 1;

            while (j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = key;
        }

    }
}