import inOut.IOUtils;
import insertSort.insert_Sort;
import SelecrionSort.SelecrionSort;
import MergeSort.MergeSort;
import QuickSort.QuickSort;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        var BaseArray = IOUtils.toIntArray(in.nextLine().split(""));
        var SortNumber = BaseArray[0];
        int[] ArrForSort=Arrays.copyOfRange(BaseArray,1,BaseArray.length);
        Instant startInclusive= Instant.now();
        long elapsed;
        switch (SortNumber){
            case 1:
                insert_Sort.insertionSort(ArrForSort);    
                break;
            case 2:
                SelecrionSort.SelecrionSort(ArrForSort);
                break;
            case 3:
                MergeSort.MergeSort(ArrForSort,ArrForSort.length);
                break;
            case 4:
                QuickSort.quickSort(ArrForSort,0,ArrForSort.length-1);
                break;
            default:
            break;
        }
        Instant endExclusive=Instant.now();  
        String EasyMenu[] = {"Insert Sort","Selecrion Sort","Merge Sort","Quick Sort",};
        in.close();
        IOUtils.writeOutput(EasyMenu[SortNumber-1], ArrForSort);
        elapsed=Duration.between(startInclusive, endExclusive).toMillis();
        System.out.println("Время выполнения сортировки, мс: " + elapsed);

    }
}
