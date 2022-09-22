package lesson1.homework.src.com.hsai.sortclasses;

public class InsertionSort extends AbstractSort {
    public InsertionSort() {
        super("Insertion sort");
    }

    public void sort(int[] array) {
        for (int j = 1; j < array.length; j++) {
            int key = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] > key) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;
        }
    }
}
