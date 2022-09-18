package lesson1.homework.src.com.hsai;

import java.util.Arrays;
import lesson1.homework.src.com.hsai.SortingMethods.*;


public class Main {

    public static void main(String[] arg){

        int[] originalArray = IOUtils.toIntArray(arg);

        int sortNumber = originalArray[0];

        int[] array = Arrays.copyOfRange(originalArray,1,originalArray.length);

        sortNumber%=4;

        if(sortNumber<0){
            sortNumber+=4;
        }

        switch (sortNumber) {
            case 0 -> {
                SelectionSort.selectionSort(array);
                IOUtils.writeOutput("\nSelection sort", array);
            }
            case 1 -> {
                InsertionSort.insertionSort(array);
                IOUtils.writeOutput("\nInsertion sort", array);
            }
            case 2 -> {
                MergeSort.mergeSort(array);
                IOUtils.writeOutput("\nMerge sort", array);
            }
            case 3 -> {
                QuickSort.quickSort(array);
                IOUtils.writeOutput("\nQuick sort", array);
            }
        }

    }
}