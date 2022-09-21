package lesson1.homework.src.com.hsai.algorithms;


public class QuickSorting extends ParentSort {
    public QuickSorting() {
        name = "Quick sorting";
    }

    boolean started = false;

    public int[] sort(int[] array, int leftBorder, int rightBorder) {
        if (!started) {
            rightBorder--;
            started = true;
        }
        if (array.length < 2) {
            return array;
        }
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = array[(leftMarker + rightMarker) / 2];
        do {while (array[leftMarker] < pivot) {
                leftMarker++;
            }
            while (array[rightMarker] > pivot) {
                rightMarker--;
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    int tmp = array[leftMarker];
                    array[leftMarker] = array[rightMarker];
                    array[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);
        if (leftMarker < rightBorder) {
            sort(array, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            sort(array, leftBorder, rightMarker);
        }
        return array;
    }

}
