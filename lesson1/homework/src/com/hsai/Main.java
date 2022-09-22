package lesson1.homework.src.com.hsai;

import lesson1.homework.src.com.hsai.sorts.InsertionSort;
import lesson1.homework.src.com.hsai.sorts.MergeSort;
import lesson1.homework.src.com.hsai.sorts.QuickSort;
import lesson1.homework.src.com.hsai.sorts.SelectionSort;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] inputArray = IOUtils.toIntArray(args);
        int choice = inputArray[0];
        int[] sortArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);

        choice %= 4;
        if (choice < 0) choice += 4;

        switch (choice) {
            case 0 -> {
                SelectionSort.selectionSort(sortArray);
                IOUtils.writeOutput("Selection", sortArray);
            }
            case 1 -> {
                InsertionSort.insertionSort(sortArray);
                IOUtils.writeOutput("Insert", sortArray);
            }
            case 2 -> {
                MergeSort.mergeSort(sortArray);
                IOUtils.writeOutput("Merge", sortArray);
            }
            case 3 -> {
                QuickSort.quickSort(sortArray);
                IOUtils.writeOutput("Quick", sortArray);
            }
        }
    }
}
