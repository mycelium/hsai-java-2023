package lesson1.homework.src.com.hsai;
import lesson1.homework.src.com.hsai.sorting.Insertion;
import lesson1.homework.src.com.hsai.sorting.Merge;
import lesson1.homework.src.com.hsai.sorting.Quick;
import lesson1.homework.src.com.hsai.sorting.Select;

public class MainSort {
    public static void main(String[] args){
        int[] arr = IOUtils.toIntArray(args);
        int choice = arr[0] % 4;
        if (choice < 0) choice+=4;
        switch (choice){

            case 0 -> {
                Select.select(arr);
                IOUtils.writeOutput("Selection sort", arr);
            }
            case 1 -> {
                Insertion.insert(arr);
                IOUtils.writeOutput("Insertion sort", arr);
            }
            case 2 -> {
                Merge.merge(arr);
                IOUtils.writeOutput("Merge sort", arr);
            }
            case 3 -> {
                Quick.quick(arr);
                IOUtils.writeOutput("Quick sort", arr);
            }
            default -> System.out.println("Something went wrong!");
        }
    }
}
