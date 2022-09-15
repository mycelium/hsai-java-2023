package lesson1.homework.src.com.hsai.algorithms;
import lesson1.homework.src.com.hsai.algorithms.ParentSort;

public class SortingByInsertion extends ParentSort
{
    public SortingByInsertion() {
        name = "Сортировка вставками";
    }
    public int[] sort(int[] array, int start, int finish)
    {
        for (int i = start + 1; i < finish; i++) {
            int x = array[i];
            int j = i;
            while ( j > start && array[j - 1] > x) {
                array[j] = array[j - 1];
                j = j - 1;
            }
            array[j] = x;
        }
        return array;
    }
}
