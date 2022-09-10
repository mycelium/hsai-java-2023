package lesson1.homework.src.com.hsai;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOUtils {

    public static int[] toIntArray(String[] args) {
        return Stream.of(args)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void writeOutput(String algorithmName, int[] array) {
        System.out.println(algorithmName);

        var output = Arrays.stream(array).mapToObj(it -> it + "").collect(Collectors.joining(" "));
        System.out.println(output);
    }
}
