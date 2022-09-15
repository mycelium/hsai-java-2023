package lesson1.homework.src.com.hsai;
import java.util.Arrays;
import lesson1.homework.src.com.hsai.IOUtils;
import lesson1.homework.src.com.hsai.algorithms.*;





public class Main {
    public static void main (String[] args) {
        ParentSort[] sorts = {new SortingByChoice(), new SortingByInsertion() , new MergeSorting(), new QuickSorting()};
        IOUtils writer = new IOUtils();
        int[] array = writer.toIntArray(args);
        int numberSort = (4 + array[0] % 4) % 4;
        int [] newArray = Arrays.copyOfRange(array,1, array.length);
        writer.writeOutput(sorts[numberSort].name, sorts[numberSort].sort(newArray, 0 , newArray.length));
        return;
    }
}
