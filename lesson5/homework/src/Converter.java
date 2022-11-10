package lesson5.homework.src;

import java.nio.charset.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import lesson5.homework.src.exeptions.*;

public class Converter {

    static private Map<String, Integer> digits = new HashMap();

    static {
        digits.put("ноль", 0);
        digits.put("один", 1);
        digits.put("два", 2);
        digits.put("три", 3);
        digits.put("четыре", 4);
        digits.put("пять", 5);
        digits.put("шесть", 6);
        digits.put("семь", 7);
        digits.put("восемь", 8);
        digits.put("девять", 9);
        digits.put("десять", 10);
        digits.put("одиннадцать", 11);
        digits.put("двенадцать", 12);
        digits.put("тринадцать", 13);
        digits.put("четырнадцать", 14);
        digits.put("пятнадцать", 15);

    }

    static private int stringToInt(String str) {
        return digits.getOrDefault(str, -1);
    }

    static private boolean isByte(int number) {
        return number <= 255;
    }

    static private int formNumber(int[] array, int index, int system) {
        int result = 0;
        for (int i = 0; i < index; i++) {
            result = result + array[i] * (int) Math.pow(system, i);

        }
        return result;
    }

    static public List<Byte> convertDataToIntArray(String data, int calculusSystem) {
        String[] numbersAsStrings = data.split(" ");
        List<Byte> numbersAsIntegers = new ArrayList();
        for (String stringNumber : numbersAsStrings) {
            int number = stringToByte(stringNumber, calculusSystem);
            //if number == 999 (uncorrect return from "stringToByte"), this is not byte, ignore)
            if (isByte(number)) {
                numbersAsIntegers.add((byte) number);
            }
        }
        return numbersAsIntegers;
    }

    static public int stringToByte(String str, int calculusSystem) {
        StringBuilder sb = new StringBuilder();
        String helper = null;
        int[] arrayOfDigits = new int[3];
        int index = 0;
        for (char symbol : str.toCharArray()) {
            sb.append(symbol);
            boolean shortString = false;
            boolean longString = false;
            if (digits.containsKey(sb.toString())) {
                shortString = true;
            }
            if (digits.containsKey(helper + sb.toString())) {
                longString = true;
            }
            if (longString || shortString) {
                if (index > 2) {            //byte cannot consist of >3 characters
                    return 999;
                }
                if (longString) {
                    if (arrayOfDigits[0] + 10 < calculusSystem) {
                        arrayOfDigits[0] += 10;
                    } else {
                        return 999;     // such digit cannot be in this calculus system
                    }
                    sb.setLength(0);
                } else {
                    int number = stringToInt(sb.toString());
                    if (number >= calculusSystem) {
                        return 999;     // such digit cannot be in this calculus system
                    }
                    for (int i = index; i != 0; i--) {
                        arrayOfDigits[i] = arrayOfDigits[i - 1];
                    }
                    arrayOfDigits[0] = number;
                    helper = sb.toString();
                    sb.setLength(0);
                    index++;
                }
            }
        }
        return Converter.formNumber(arrayOfDigits, index, calculusSystem);
    }

    static public Charset stringToEncoding(String str) throws InvalidCharsetExeption {
        switch (str) {
            case "UTF-16":
                return StandardCharsets.UTF_16;
            case "UTF-8":
                return StandardCharsets.UTF_8;
            case "ASCII":
                return StandardCharsets.US_ASCII;
            default:
                throw new InvalidCharsetExeption(str);
        }
    }
}
