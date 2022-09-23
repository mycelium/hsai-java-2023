package lesson1.homework.src.com.hsai.algorithms;


public class SortingByChoice extends ParentSort {
    public SortingByChoice() {
        name = "Sorting by choice";
    }

    public int[] sort(int[] array, int start, int finish) {
        for (int i = start; i < finish; i++) {
            int min = i;
            for (int j = i + 1; j < finish; j++) {
                if (array[j] < array[min]) min = j;
            }
            if (min != i) {
                int c = array[i];
                array[i] = array[min];
                array[min] = c;
            }
        }
        return array;
    }
}
