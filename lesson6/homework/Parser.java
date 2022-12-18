package lesson6.homework;

import java.util.Map;
import java.util.HashMap;
import java.nio.charset.*;
import java.util.ArrayList;

public class Parser {
    private static final Map<String, Integer> textToInt = new HashMap();
    private static final Integer maxLength;

    static {
        textToInt.put("ноль", 0);
        textToInt.put("один", 1);
        textToInt.put("два", 2);
        textToInt.put("три", 3);
        textToInt.put("четыре", 4);
        textToInt.put("пять", 5);
        textToInt.put("шесть", 6);
        textToInt.put("семь", 7);
        textToInt.put("восемь", 8);
        textToInt.put("девять", 9);
        textToInt.put("десять", 10);
        textToInt.put("одиннадцать", 11);
        textToInt.put("двенадцать", 12);
        textToInt.put("тринадцать", 13);
        textToInt.put("четырнадцать", 14);
        textToInt.put("пятнадцать", 15);

        int max = 0;
        int amount = textToInt.keySet().size();
        String[] keys = new String[amount];
        textToInt.keySet().toArray(keys);
        for (int i = 0; i < amount; i++) {
            int wordLength = keys[i].length();
            if (wordLength > max) {
                max = wordLength;
            }
        }
        maxLength = max;
    }

    public static String parse(String input, int numberSystem, String encoding) {
        if (numberSystem < 2) numberSystem = 2;
        if (numberSystem > 16) numberSystem = 16;
        Charset charset = switch (encoding) {
            case "UTF-8" -> StandardCharsets.UTF_8;
            case "UTF-16" -> StandardCharsets.UTF_16;
            default -> StandardCharsets.US_ASCII;
        };

        String[] textBytes = input.split(" ");
        ArrayList<Byte> byteArray = new ArrayList<>();
        for (int i = 0; i < textBytes.length; i++) {
            int tokenValue = 0;
            String token = textBytes[i];
            boolean valid = true;
            while (valid && token.length() > 0) {
                String chop = chopToken(token);
                int digit = -1;
                if (!chop.isEmpty()) digit = textToInt.get(chop);
                if (digit == -1 || digit >= numberSystem) valid = false;
                else {
                    tokenValue = tokenValue * numberSystem + digit;
                    token = token.substring(chop.length());
                }
            }
            //System.out.println(tokenValue);
            if (valid && tokenValue < 256) {
                byteArray.add((byte) tokenValue);
            }
        }
        byte[] bytes = new byte[byteArray.size()];
        for (int i = 0; i < byteArray.size(); i++) {
            bytes[i] = byteArray.get(i);
        }
        return new String(bytes, charset);
    }

    private static String chopToken(String token) {
        int value = -1;
        String result = "";
        for (int i = 1; (i <= maxLength && i <= token.length()); i++) {
            int chop = textToInt.getOrDefault(token.substring(0, i), -1);
            if (chop > value) {
                value = chop;
                result = token.substring(0, i);
            }
        }
        return result;
    }

}
