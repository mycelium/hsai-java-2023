package lesson1.homework.src.com.hsai;
import lesson1.homework.src.com.hsai.sort.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args)
    {
        int[] input = IOUtils.toIntArray(args);
        int variant = input[0];
        variant %= 4;
        if (variant < 0) variant += 4;
        int[] array = Arrays.copyOfRange(input, 1, input.length);

        switch(variant)
        {
            case 0:
            {
                SelectionSort.selectionSort(array);
                IOUtils.writeOutput("selection sort", array);
                break;
            }
            case 1:
            {
                InsertionSort.insertionSort(array);
                IOUtils.writeOutput("insertion sort", array);
                break;
            }
            case 2:
            {
                MergeSort.mergeSort(array);
                IOUtils.writeOutput("merge sort", array);
                break;
            }
            case 3:
            {
                QuickSort.quickSort(array);
                IOUtils.writeOutput("quick sort", array);
                break;
            }
            default:
            {
                int[] error = {0};
                IOUtils.writeOutput("Out of Bounds Index!", error);
            }
        }
    }
}
