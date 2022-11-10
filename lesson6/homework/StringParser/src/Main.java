import utils.StringParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] fileContent = Files.readString(Path.of(args[0])).split(" ");
        fileContent = StringParser.numStringToDigitString(fileContent);
        String outputString = new String(StringParser.translateDigitStringToByteArray(fileContent, Integer.parseInt(args[1])), StringParser.getCharset(args[2]));
        System.out.print(outputString);
    }
}