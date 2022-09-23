import alghorithm.InsertSort;
import alghorithm.MergeSort;
import alghorithm.QuickSort;
import alghorithm.SelecrionSort;
import inOut.IOUtils;
import java.util.Arrays;

public class App {

  public static void main(String[] args) throws Exception {
    var base_array = IOUtils.toIntArray(args);
    var sort_number = base_array[0];
    int[] аrr_for_sort = Arrays.copyOfRange(base_array, 1, base_array.length);
    while (sort_number < 0) {
      sort_number += 4;
    }
    while (sort_number > 3) {
      sort_number -= 4;
    }
    switch (sort_number) {
      case 0:
        InsertSort.InsertionSort(аrr_for_sort);
        break;
      case 1:
        SelecrionSort.SelecrionSort(аrr_for_sort);
        break;
      case 2:
        MergeSort.MergeSort(аrr_for_sort, аrr_for_sort.length);
        break;
      case 3:
        QuickSort.QuickSort(аrr_for_sort, 0, аrr_for_sort.length - 1);
        break;
      default:
        break;
    }
    String EasyMenu[] = {
      "Insert Sort",
      "Selecrion Sort",
      "Merge Sort",
      "Quick Sort",
    };
    IOUtils.writeOutput(EasyMenu[sort_number], аrr_for_sort);
  }
}
