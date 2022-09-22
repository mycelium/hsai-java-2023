package lesson1.homework.src.com.hsai.sorts;

public class Choice {
    public static void choice(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min_array = array[i];
            int min_ind = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < min_array) {
                    min_array = array[j];
                    min_ind = j;
                }
            }
            int tmp_array = array[i];
            array[i] = min_array;
            array[min_ind] = tmp_array;
        }
    }
}
