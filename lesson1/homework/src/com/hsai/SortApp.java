package com.hsai;

import java.util.Arrays;
import com.hsai.auxclasses.AlgDeterm;
import com.hsai.sortalgs.*;

public class SortApp {
    public static final int ALG_CNT = 4;
    public static void main(String[] args) {
        int[] intArgs = Arrays.copyOf(IOUtils.toIntArray(args), args.length);
        int algId = AlgDeterm.Determine(intArgs[0]);
        AbstractSort sort;
        switch (algId) {
            case 0:
                sort = new SelectionSort();
                break;
            case 1:
                sort = new MergeSort();
                break;
            case 2:
                sort = new InsertionSort();
                break;
            case 3:
                sort = new QuickSort();
                break;
            default:
        }
        //sort.Sort(array);
    }
}