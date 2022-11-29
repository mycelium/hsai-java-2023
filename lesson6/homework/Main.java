package lesson6.homework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static void copyArray(byte[] source, byte[] dest){
        System.arraycopy(source,0, dest, 0, source.length);
    }
    public static void main(String[] args) throws IOException{
        String[] resultFromExtractBytes;
        String fileContent = Files.readString(Path.of(args[0]));
        String[] fileContentAfterSplit = fileContent.split(" ");
        resultFromExtractBytes = FileParsing.extractBytes(fileContentAfterSplit);
        String output = new String(FileParsing.baseNumberFromBytes(resultFromExtractBytes, Integer.parseInt(args[1])), FileParsing.textEncoding(args[2]));
        System.out.println("Program launched!");
        System.out.print("Converted string from file: ");
        System.out.print(output);
    }
}
