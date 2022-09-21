package lesson1.homework.src.com.hsai.sort;

public class SelectionSort {
    public static void selectionSort(int[] array)
    {

        for (int i = 0; i < array.length - 1; i++)
        {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++)
            {
                if (array[j] < array[minIdx]) minIdx = j;
            }
            if (minIdx != i)
            {
                int tmp = array[i];
                array[i] = array[minIdx];
                array[minIdx] = tmp;
            }
        }
    }
}
