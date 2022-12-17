package lesson1.homework.src.com.hsai.sorting;

public class Select {
    public static void select(int[] arr) {

        int size = arr.length;

        for (int step = 0; step < size - 1; step++) {

            int min_idx = step;

            for (int i = step + 1; i < size; i++) {

                if (arr[i] < arr[min_idx]) {
                    min_idx = i;
                }
            }
            int temp = arr[step];
            arr[step] = arr[min_idx];
            arr[min_idx] = temp;
        }
    }
}

