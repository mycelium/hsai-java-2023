package lesson1.homework.src.com.hsai;

import java.util.Arrays;
import lesson1.homework.src.com.hsai.IOUtils;
import lesson1.homework.src.com.hsai.SortAlgs.*;

public class Main {
    public static void main(String[] args) {
        int[] argsArr = IOUtils.toIntArray(args);
        int[] arr = Arrays.copyOfRange(argsArr, 1, argsArr.length);
        int input = argsArr[0] % 4;
        if (input < 0) input += 4;

        switch (input) {
            case 0:
                Selection.sort(arr);
                IOUtils.writeOutput("Selection sort:", arr);
                break;
            case 1:
                Insertion.sort(arr);
                IOUtils.writeOutput("Insertion sort:", arr);
                break;
            case 2:
                Merge.sort(arr);
                IOUtils.writeOutput("Merge sort:", arr);
                break;
            case 3:
                Quick.sort(arr);
                IOUtils.writeOutput("Quick sort:", arr);
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
}