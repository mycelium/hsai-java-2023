package lesson5.homework.src;


import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

import lesson5.homework.src.exeptions.*;

public class BytesReader {

    static public String readAllFile(String fileName) throws NoSuchFileException {
        try {
            String fileContent = Files.readString(Path.of(fileName));
            return fileContent;
        } catch (Exception e) {
            throw new NoSuchFileException(fileName);
        }
    }


    static public void bytesToString(List<Byte> bytes, String encoding) {
        byte[] arrayOfBytes = new byte[bytes.size()];
        int i = 0;
        for (byte b : bytes) {
            arrayOfBytes[i] = b;
            i++;
        }
        try {
            String s = new String(arrayOfBytes, Converter.stringToEncoding(encoding));
            System.out.println(s);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
