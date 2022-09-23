package alghorithm;

public class SelecrionSort {

  public static void swap(int[] arr, int i, int j) {
    arr[i] = (arr[i] + arr[j]) - (arr[j] = arr[i]);
  }

  public static void SelecrionSort(int[] array) {
    for (int left = 0; left < array.length; left++) {
      int minInd = left;
      for (int i = left; i < array.length; i++) {
        if (array[i] < array[minInd]) {
          minInd = i;
        }
      }
      swap(array, left, minInd);
    }
  }
}
