package com.hsai;

import java.util.Arrays;
import com.hsai.additional.*;
import com.hsai.algorithms.*;

public class SortApp {
    public static void main(String[] args) {
        int[] intArgs = Arrays.copyOf(IOUtils.toIntArray(args), args.length);
        int algId = SelectAlg.determineAlg(intArgs[0]);
        int[] array = Arrays.copyOfRange(intArgs, 1, intArgs.length);

        AbstractSort sort = null;
        switch (algId) {
            case 0 -> sort = new Selection();
            case 1 -> sort = new Insertion();
            case 2 -> sort = new Merge();
            case 3 -> sort = new Quick();
            default -> {
                System.out.println("Invalid algorithm ID");
                return;
            }
        }
        sort.sort(array);
        IOUtils.writeOutput(sort.getName(), array);
    }
}
