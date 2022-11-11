package lesson6.homework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        try {
            String fileContent = Files.readString(Path.of(args[0]));
            System.out.println(Parser.parse(fileContent, Integer.parseInt(args[1]), args[2]));
        } catch (IOException e) {
            System.out.println("No such file found!");
        }
    }
}
