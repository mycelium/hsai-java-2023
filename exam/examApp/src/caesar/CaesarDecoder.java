package caesar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CaesarDecoder {
    private static int val = 5243167;
    public static String decode() throws IOException {
        String fileContent = Files.readString(Path.of("C:\\Users\\sakralen\\Desktop\\exam\\hsai-java-2023\\exam\\tasks\\task11"));
        Stream<Character> stream = fileContent.codePoints().mapToObj(c -> (char)(c - val));
        String result = stream.map(c->c.toString()).collect(Collectors.joining());
        return stream.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(decode());
    }
}
