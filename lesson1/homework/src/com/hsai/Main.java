package lesson1.homework.src.com.hsai;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import lesson1.homework.src.com.hsai.algorithms.*;

import static lesson1.homework.src.com.hsai.algorithms.SortManager.chooseSort;


public class Main {
    public static void main(String[] args) {
        int[] numbers = IOUtils.toIntArray(args);
        int sortChoice = numbers[0] % 4;
        int[] unsortedNums = Arrays.copyOfRange(numbers, 1, numbers.length);
        System.out.println(Arrays.toString(unsortedNums));

        chooseSort();

        switch (sortChoice){
            case 0:
                QuickSort algo = new QuickSort();

                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    enum SortType {
        SELECTION, INSERTION, MERGE, QUICK
    }

    private static final Map<SortType, Consumer<Integer[]>> sorts = Map.of(SortType.SELECTION, (e) -> {
        int x;
        SelectionSort::sort(e);
    });

    private static void chooseAndSort(int[] array, SortType type) {

    }
}