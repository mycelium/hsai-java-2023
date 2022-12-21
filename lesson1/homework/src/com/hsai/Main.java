package lesson1.homework.src.com.hsai;

import lesson1.homework.src.com.hsai.sorts.Choice;
import lesson1.homework.src.com.hsai.sorts.Insert;
import lesson1.homework.src.com.hsai.sorts.Merge;
import lesson1.homework.src.com.hsai.sorts.Quick;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int for_slc = IOUtils.toIntArray(args)[0] % 4;
        if(for_slc < 0) for_slc = for_slc + 4;
        int[] array_for_sort = Arrays.copyOfRange(IOUtils.toIntArray(args), 1, IOUtils.toIntArray(args).length);
        switch (for_slc) {
            case (0) -> {
                Choice.choice(array_for_sort);
                IOUtils.writeOutput("choice sort", array_for_sort);
            }
            case (1) -> {
                Insert.insert(array_for_sort);
                IOUtils.writeOutput("insert sort", array_for_sort);
            }
            case (2) -> {
                Merge.merge(array_for_sort, 0, array_for_sort.length - 1);
                IOUtils.writeOutput("merge sort", array_for_sort);
            }
            case (3) -> {
                Quick.quick(array_for_sort, 0, array_for_sort.length - 1);
                IOUtils.writeOutput("quick sort", array_for_sort);
            }
        }
        }
}