package lesson5.homework.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BytesReader {

    static public String readAllFile(String fileName) {
        String s;
        StringBuilder sb = new StringBuilder();
        FileReader ifstream = null;
        Exception ex = null;
        try {
            ifstream = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(ifstream);
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }
            reader.close();
            ifstream.close();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }

        return sb.toString();
    }


    static public void bytesToString(List<Byte> bytes, String encoding) {
        byte[] arrayOfBytes = new byte[bytes.size()];
        int i = 0;
        for (byte b : bytes) {
            arrayOfBytes[i] = b;
            i++;
        }
        String s = new String(arrayOfBytes, Converter.stringToEncoding(encoding));
        System.out.println(s);
    }
}
