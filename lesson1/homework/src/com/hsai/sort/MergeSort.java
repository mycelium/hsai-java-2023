package lesson1.homework.src.com.hsai.sort;

public class MergeSort {

    public static void sort(int[] array) {
        sortWithParameters(array, 0, array.length - 1);
    }

    private static void sortWithParameters(int[] array, int left, int right) {
        if (left < right) {
            int middle = left  + (right  - left) / 2;

            sortWithParameters(array, left, middle);
            sortWithParameters(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int firstSize = middle - left + 1;
        int secondSize = right - middle;

        int[] firstArray = new int[firstSize];
        int[] secondArray = new int[secondSize];

        System.arraycopy(array, left, firstArray, 0, firstSize);
        System.arraycopy(array, middle + 1, secondArray, 0, secondSize);

        int initialIndexFirst = 0;
        int initialIndexSecond = 0;
        int initialIndexMerged = left;

        while (initialIndexFirst < firstSize && initialIndexSecond < secondSize) {
            if (firstArray[initialIndexFirst] <= secondArray[initialIndexSecond]) {
                array[initialIndexMerged] = firstArray[initialIndexFirst];
                initialIndexFirst++;
            } else {
                array[initialIndexMerged] = secondArray[initialIndexSecond];
                initialIndexSecond++;
            }

            initialIndexMerged++;
        }

        while (initialIndexFirst < firstSize) {
            array[initialIndexMerged] = firstArray[initialIndexFirst];
            initialIndexFirst++;
            initialIndexMerged++;
        }

        while (initialIndexSecond < secondSize) {
            array[initialIndexMerged] = secondArray[initialIndexSecond];
            initialIndexSecond++;
            initialIndexMerged++;
        }
    }
}
