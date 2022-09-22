package lesson1.homework.src.com.hsai.sorts;

public class Quick {
    static int partition(int[] array, int begin, int end) {
        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] < array[end]) {
                int tmp = array[counter];
                array[counter] = array[i];
                array[i] = tmp;
                counter++;
            }
        }
        int tmp = array[end];
        array[end] = array[counter];
        array[counter] = tmp;
        return counter;
    }

    public static void quick(int[] array, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        quick(array, begin, pivot-1);
        quick(array, pivot+1, end);
    }
}