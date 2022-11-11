package lesson6.homework;

import lesson6.homework.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {
    public static void main(String[] args) throws IOException {
        String fileContent = Files.readString(Path.of(args[0]));
        String[] contentArray = fileContent.split(" ");
        int numberSystem = Integer.parseInt(args[1]);
        String inputCharset = args[2];

        Parser str = new Parser(contentArray, numberSystem, inputCharset);
        byte[] resultArray = str.toUserNumberSystemConvert(str.writtenToDigitsConvert());

        System.out.println(str.charsetConverter(resultArray));
    }

}
