package lesson6.homework.StringParser.src;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileContent = Files.readString(Path.of(args[0]));
        StringConverter stringConverter =
                new StringConverter(fileContent.split(" "), Integer.parseInt(args[1]), args[2]);
        stringConverter.printBytesArr(stringConverter.convertStrToByteArr());
    }
}