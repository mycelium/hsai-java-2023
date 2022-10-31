package lesson5.homework.src;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String s = BytesReader.readAllFile(args[0]);
        List<Byte> arrayData = Converter.convertDataToIntArray(s, Integer.parseInt(args[1]));
        BytesReader.bytesToString(arrayData, args[2]);
    }
}
