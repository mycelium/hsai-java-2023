package lesson1.homework.src.com.hsai.sorts;

public class Insert {
    public static void insert(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            int tmp_from_array = array[i];
            while(j >= 0 && tmp_from_array < array[j]) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = tmp_from_array;
        }
    }
}
