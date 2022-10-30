package lesson1.homework.src.com.hsai;

import java.util.Arrays;
import lesson1.homework.src.com.hsai.auxclasses.AlgDeterm;
import lesson1.homework.src.com.hsai.sortalgs.*;

public class SortApp {

    public static void main(String[] args) {
        int[] intArgs = Arrays.copyOf(IOUtils.toIntArray(args), args.length);
        int algId = AlgDeterm.determine(intArgs[0]);
        int[] array = Arrays.copyOfRange(intArgs, 1, intArgs.length);

        AbstractSort sort = null;
        switch (algId) {
            case 0:
                sort = new SelectionSort();
                break;
            case 1:
                sort = new InsertionSort();
                break;
            case 2:
                sort = new MergeSort();
                break;
            case 3:
                sort = new QuickSort();
                break;
            default:
                System.out.println("Invalid algorithm ID");
                return;
        }

        sort.sort(array);
        IOUtils.writeOutput(sort.getName(), array);
    }
}