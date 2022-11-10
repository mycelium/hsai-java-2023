package utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class StringParser {
    private static HashMap<String, String> numStringToDigitStringMap = new HashMap<>();
    private static HashMap<Character, Integer> digitStringToIntMap = new HashMap<>();
    static {
        numStringToDigitStringMap.put("ноль", "0");
        numStringToDigitStringMap.put("один", "1");
        numStringToDigitStringMap.put("два", "2");
        numStringToDigitStringMap.put("три", "3");
        numStringToDigitStringMap.put("четыре", "4");
        numStringToDigitStringMap.put("пять", "5");
        numStringToDigitStringMap.put("шесть", "6");
        numStringToDigitStringMap.put("семь", "7");
        numStringToDigitStringMap.put("восемь", "8");
        numStringToDigitStringMap.put("девять", "9");
        numStringToDigitStringMap.put("десять", "A");
        numStringToDigitStringMap.put("одиннадцать", "B");
        numStringToDigitStringMap.put("двенадцать", "C");
        numStringToDigitStringMap.put("тринадцать", "D");
        numStringToDigitStringMap.put("четырнадцать", "E");
        numStringToDigitStringMap.put("пятнадцать", "F");

        digitStringToIntMap.put('0', 0);
        digitStringToIntMap.put('1', 1);
        digitStringToIntMap.put('2', 2);
        digitStringToIntMap.put('3', 3);
        digitStringToIntMap.put('4', 4);
        digitStringToIntMap.put('5', 5);
        digitStringToIntMap.put('6', 6);
        digitStringToIntMap.put('7', 7);
        digitStringToIntMap.put('8', 8);
        digitStringToIntMap.put('9', 9);
        digitStringToIntMap.put('A', 10);
        digitStringToIntMap.put('B', 11);
        digitStringToIntMap.put('C', 12);
        digitStringToIntMap.put('D', 13);
        digitStringToIntMap.put('E', 14);
        digitStringToIntMap.put('F', 15);
    }

    public static String[] numStringToDigitString(String[] stringArray){
        String[] resStringArray = new String[stringArray.length];
        for (int i = 0; i<stringArray.length; i++) {
            String resString = "";
            String bufString = "";
            for (int j = 0; j<stringArray[i].length(); j++) {
                bufString += stringArray[i].charAt(j);
                if (numStringToDigitStringMap.containsKey(bufString))
                    if (!(stringArray[i].startsWith("на", j + 1))) {
                        resString += numStringToDigitStringMap.get(bufString);
                        bufString = "";
                    }
            }
            resStringArray[i] = resString;
        }
        return resStringArray;
    }

    public static byte[] translateDigitStringToByteArray(String[] stringArray, int numSystem){
        byte[] outputArray = new byte[0];
        for (String item:stringArray) {
            int bufInt = 0;
            for (int i = 0; i<item.length(); i++){
                if (digitStringToIntMap.get(item.charAt(item.length()-1-i))<numSystem){
                    bufInt += digitStringToIntMap.get(item.charAt(item.length()-1-i))*Math.pow(numSystem, i);
                }
                else break;
            }
            if (bufInt<256) {
                byte[] newArray = new byte[outputArray.length+1];
                for (int j = 0; j<outputArray.length; j++) {
                    newArray[j] = outputArray[j];
                }
                newArray[newArray.length-1] = (byte)bufInt;
                outputArray = newArray;
            }
        }
        return outputArray;
    }

    public static Charset getCharset(String charset) {
        switch (charset) {
            case "ASCII":
                return StandardCharsets.US_ASCII;
            case "UTF-8":
                return StandardCharsets.UTF_8;
            case "UTF-16":
                return StandardCharsets.UTF_16;
            default:
                return StandardCharsets.US_ASCII;
        }
    }
}
