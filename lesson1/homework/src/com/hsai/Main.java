package lesson1.homework.src.com.hsai;

import lesson1.homework.src.com.hsai.sort.InsertionSort;
import lesson1.homework.src.com.hsai.sort.MergeSort;
import lesson1.homework.src.com.hsai.sort.QuickSort;
import lesson1.homework.src.com.hsai.sort.SelectionSort;

public class Main {

    public static void main(String[] args) {
        int[] input = IOUtils.toIntArray(args);

        if (input.length == 0) {
            System.out.println("Incorrect input.");
        } else if (input.length == 1) {
            System.out.println("There is nothing to sort.");
        } else {
            int[] array = new int[input.length - 1];
            System.arraycopy(input, 1, array, 0, input.length - 1);

            int algorithmNumber = input[0];
            algorithmNumber %= 4;
            if (algorithmNumber < 0) {
                algorithmNumber += 4;
            }

            switch (algorithmNumber) {
                case 0:
                    SelectionSort.sort(array);
                    IOUtils.writeOutput("Selection sort", array);
                    break;
                case 1:
                    InsertionSort.sort(array);
                    IOUtils.writeOutput("Insertion sort", array);
                    break;
                case 2:
                    MergeSort.sort(array);
                    IOUtils.writeOutput("Merge sort", array);
                    break;
                case 3:
                    QuickSort.sort(array);
                    IOUtils.writeOutput("Quick sort", array);
                    break;
                default:
                    break;
            }
        }
    }
}
