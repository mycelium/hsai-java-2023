package lesson1.homework.src.com.hsai;

import java.util.Arrays;

import lesson1.homework.src.com.hsai.algorithms.*;



public class Main {
    public static void main(String[] args) {
        int[] rawNumbers = IOUtils.toIntArray(args);
//      calculating the correct always positive remainder
        int sortChoice = (((rawNumbers[0] % 4) + 4) % 4);
        int[] numbers = Arrays.copyOfRange(rawNumbers, 1, rawNumbers.length);

        String[] sortAlgos = {
            "Selection Sort",
            "Insertion Sort",
            "Merge Sort",
            "Quick Sort",
        };

        switch (sortChoice) {
            case 0 -> SelectionSort.sort(numbers);
            case 1 -> InsertionSort.sort(numbers);
            case 2 -> MergeSort.sort(numbers, 0, numbers.length - 1);
            case 3 -> QuickSort.sort(numbers, 0, numbers.length - 1);
            default -> {
            }
        }
        IOUtils.writeOutput(sortAlgos[sortChoice], numbers);
    }
}