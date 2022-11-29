package lesson5.homework.src;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import lesson5.homework.src.exeptions.*;

public class Main {
    public static void main(String[] args) {
        try {
            String s = BytesReader.readAllFile(args[0]);
            List<Byte> arrayData = Converter.convertDataToIntArray(s, Integer.parseInt(args[1]));
            BytesReader.bytesToString(arrayData, args[2]);
        } catch (NoSuchFileException e) {
            System.err.println(e);
        }
    }
}
