package lesson6.homework;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static lesson6.homework.Main.copyArray;

public class FileParsing {
    static private final HashMap<String, String> textByte = new HashMap<>();
    static private final HashMap<Character, Integer> byteNumber = new HashMap<>();
    static private final String[] textNumber = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двеннадцать", "тринадцать", "четырнадцать", "пятнадцать"};
    static private final String[] Number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
    static private final Character[] Byte = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static {
        for (int i = 0; i < 16; i++) {
            textByte.put(textNumber[i], Number[i]);
            byteNumber.put(Byte[i], i);
        }
    }

    public static Charset textEncoding(String encoding) {
        switch (encoding) {
            case "ASCII":
                return StandardCharsets.US_ASCII;
            case "UTF-8":
                return StandardCharsets.UTF_8;
            case "UTF-16":
                return StandardCharsets.UTF_16;
            default:
                break;
        }
        return null;
    }

    public static String[] extractBytes(String[] array) {
        String[] convertArray = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            StringBuilder res = new StringBuilder();
            String tmp = "";
            for (int j = 0; j < array[i].length(); j++) {
                tmp += array[i].charAt(j);
                if (textByte.containsKey(tmp)) {
                    if (!(array[i].endsWith("надцать"))) {
                        res.append(textByte.get(tmp));
                        tmp = "";
                    }
                }
            }
            convertArray[i] = res.toString();
        }
        return convertArray;
    }

    public static byte[] baseNumberFromBytes(String[] array, int numberSystem) {
        int max = 256;
        byte[] convertArray = new byte[0];
        for (String it : array) {
            int tmp = 0;
            for (int i = 0; i < it.length(); i++) {
                char key = it.charAt(it.length() - i - 1);
                if (byteNumber.get(key) < numberSystem) {
                    tmp += byteNumber.get(key) * Math.pow(numberSystem, i);
                } else break;
            }
            if (tmp < max) {
                byte[] newArray = new byte[convertArray.length + 1];
                copyArray(convertArray, newArray);
                newArray[newArray.length - 1] = (byte) tmp;
                convertArray = newArray;
            }
        }
        return convertArray;
    }
}
