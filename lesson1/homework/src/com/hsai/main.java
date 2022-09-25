package lesson1.homework.src.com.hsai;

import java.util.Arrays;
import lesson1.homework.src.com.hsai.SortAlgs.*;

public class main {

    public static void main(String[] arg) {
        int[] argsArray = IOUtils.toIntArray(arg);
        int sortAlgNumber = argsArray[0];
        int[] array = Arrays.copyOfRange(argsArray, 1, argsArray.length);

        sortAlgNumber %= 4;

        if (sortAlgNumber < 0) {
            sortAlgNumber += 4;
        }

        switch (sortAlgNumber) {
            case 0:
                SelectionSort.selectionSort(array);
                IOUtils.writeOutput("Selection sort", array);
                break;
            case 1:
                InsertionSort.insertionSort(array);
                IOUtils.writeOutput("Insertion sort", array);
                break;
            case 2:
                MergeSort.mergeSort(array);
                IOUtils.writeOutput("Merge sort", array);
                break;
            case 3:
                QuickSort.quickSort(array);
                IOUtils.writeOutput("Quick sort", array);
                break;      
            default:
                System.out.println("Error! Can't choose an algorithm!");
        }

    }
}
