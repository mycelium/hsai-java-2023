package lesson1.homework.src.com.hsai.algorithms;
import lesson1.homework.src.com.hsai.algorithms.ParentSort;


public class QuickSorting extends ParentSort {
    public QuickSorting() {
        name = "Быстрая сортировка";
    }
    boolean started = false;
    public int[] sort(int[] array, int leftBorder, int rightBorder)
    {
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
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (array[leftMarker] < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (array[rightMarker] > pivot) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    int tmp = array[leftMarker];
                    array[leftMarker] = array[rightMarker];
                    array[rightMarker] = tmp;
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            sort(array, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            sort(array, leftBorder, rightMarker);
        }
        return array;
    }

}
