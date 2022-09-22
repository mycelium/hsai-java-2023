package lesson1.homework.src.com.hsai;

import java.util.Arrays;
import lesson1.homework.src.com.hsai.utils.*;
import lesson1.homework.src.com.hsai.sortclasses.*;

public class Main {
	public static void main(String[] args) {
		int[] sortArgs = Arrays.copyOf(IOUtils.toIntArray(args), args.length);
		int algNum = AlgDeterm.determine(sortArgs[0]);
		int[] sortArray = Arrays.copyOfRange(sortArgs, 1, sortArgs.length);
		
		AbstractSort sort = null;
		
		switch(algNum){
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

        sort.sort(sortArray);
        IOUtils.writeOutput(sort.getName(), sortArray);
    }
}
