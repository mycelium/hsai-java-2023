package com.hsai;

import java.util.Arrays;
import com.hsai.additional.*;
import com.hsai.algorithms.*;

public class SortApp {
    public static void main(String[] args) {
        int[] intArgs = Arrays.copyOf(IOUtils.toIntArray(args), args.length);
        int algId = DetermAlg.determine(intArgs[0]);
        int[] array = Arrays.copyOfRange(intArgs, 1, intArgs.length);

        AbstractSort sort = null;
        switch (algId) {
            case 0:
                sort = new Selection();
                break;
            case 1:
                sort = new Insertion();
                break;
            case 2:
                sort = new Merge();
                break;
            case 3:
                sort = new Quick();
                break;
            default:
                System.out.println("Invalid algorithm ID");
                return;
        }
        sort.sort(array);
        IOUtils.writeOutput(sort.getName(), array);
    }
}
