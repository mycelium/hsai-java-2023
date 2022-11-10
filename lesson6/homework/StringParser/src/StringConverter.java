package lesson6.homework.StringParser.src;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.io.IOException;
import java.io.FileWriter;

public class StringConverter {
    static private final LinkedHashMap<String, Integer> nums = new LinkedHashMap<>();
    static private final LinkedHashMap<String, String> codes = new LinkedHashMap<>();
    static private final String[] strNums = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать"};
    static private final Integer[] intNums = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    static private final String[] strCodes = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    static {
        for(int i = 0; i < intNums.length; i++) {
            nums.put(strNums[i], intNums[i]);
            codes.put(strNums[i], strCodes[i]);
        }
    }

    private final String[] stringFromFile;
    private final int base;
    private final String encoding;

    public StringConverter(String[] s, int b, String e) {
        stringFromFile = s;
        base = b;
        encoding = e;
    }

    private int correctBaseNumber(String byteStr) {
        int corrNum = 0;

        for (int i = 0; i < byteStr.length(); i++) {
            int digit = Integer.parseInt(String.valueOf(byteStr.charAt(byteStr.length() - i - 1)), base);
            if (digit >= base) return -1;

            corrNum += digit * (int) Math.pow(base, i);
        }

        if (corrNum > 255) return -1;

        return corrNum;
    }
    private int strToByte(String str) {
        String[] keys = codes.keySet().toArray(new String[0]);

        StringBuilder strNumber = new StringBuilder(str);

        for (int i = keys.length - 1; i >= 0; i--) {
            strNumber = sbReplaceAll(strNumber, keys[i], codes.get(keys[i]));
        }

        return correctBaseNumber(strNumber.toString());
    }

    public List<Byte> convertStrToByteArr() {
        List<Byte> convertedNumbers = new ArrayList();

        for (String currString : stringFromFile) {
            int num = strToByte(currString);
            if(num != -1) convertedNumbers.add((byte)num);
        };

        return convertedNumbers;
    }
    private static StringBuilder sbReplaceAll(StringBuilder sb, String str1, String str2) {
        StringBuilder res = new StringBuilder(sb);
        int index = res.indexOf(str1);

        while (index != -1) {
            res.replace(index, index + str1.length(), str2);
            index = res.indexOf(str1, index + str2.length());
        }
        return res;
    }

    public void printBytesArr(List<Byte> arr) throws IOException {
        byte[] bytesArray = new byte[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            bytesArray[i] = arr.get(i);
        }

        String stringToPrint = new String(bytesArray, Charset.forName(encoding));
        System.out.println(stringToPrint);

        try(FileWriter fw = new FileWriter("output.txt", Charset.forName(encoding), false)) {
            fw.write(stringToPrint);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
