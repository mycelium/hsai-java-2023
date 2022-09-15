package lesson1.homework.src.com.hsai.algorithms;
import lesson1.homework.src.com.hsai.algorithms.ParentSort;


public class SortingByChoice extends ParentSort {
    public SortingByChoice() {
        name = "Сортировка выбором";
    }
    public int[] sort(int[] array, int start,int finish)
    {
        for (int i = start; i < finish; i++) {
            int min = i;
            for (int j = i + 1; j < finish ; j++) {
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
