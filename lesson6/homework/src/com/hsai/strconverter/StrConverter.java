package com.hsai.strconverter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StrConverter {
    private static LinkedHashMap<String, String> wordToNum = new LinkedHashMap<String, String>();
    private static LinkedHashMap<String, Integer> numToDig = new LinkedHashMap<String, Integer>();

    static {
        wordToNum.put("ноль", "0");
        wordToNum.put("один", "1");
        wordToNum.put("два", "2");
        wordToNum.put("три", "3");
        wordToNum.put("четыре", "4");
        wordToNum.put("пять", "5");
        wordToNum.put("шесть", "6");
        wordToNum.put("семь", "7");
        wordToNum.put("восемь", "8");
        wordToNum.put("девять", "9");
        wordToNum.put("десять", "A");
        wordToNum.put("одиннадцать", "B");
        wordToNum.put("двенадцать", "C");
        wordToNum.put("тринадцать", "D");
        wordToNum.put("четырнадцать", "E");
        wordToNum.put("пятнадцать", "F");

        numToDig.put("0", 0);
        numToDig.put("1", 1);
        numToDig.put("2", 2);
        numToDig.put("3", 3);
        numToDig.put("4", 4);
        numToDig.put("5", 5);
        numToDig.put("6", 6);
        numToDig.put("7", 7);
        numToDig.put("8", 8);
        numToDig.put("9", 9);
        numToDig.put("A", 10);
        numToDig.put("B", 11);
        numToDig.put("C", 12);
        numToDig.put("D", 13);
        numToDig.put("E", 14);
        numToDig.put("F", 15);
    }

    public static void parse(String str, int numSys, Charset charset) {
        String codesStr = toCodes(str);
        String[] tokens = removeInvalidTokens(codesStr, numSys);
        byte[] bytes = convertToByteArray(tokens, numSys);
        System.out.println(new String(bytes, charset)); 
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
                System.err.println("Charset is imposter");
                System.exit(-1);
                return StandardCharsets.ISO_8859_1;
        }
    }

    private static String toCodes(String str) {
        String[] keys = wordToNum.keySet().toArray(new String[0]);
        StringBuilder res = new StringBuilder(str);

        for (int i = keys.length - 1; i >= 0; i--) {
            res = sbReplaceAll(res, keys[i], wordToNum.get(keys[i]));
        }
        return res.toString();
    }

    private static StringBuilder sbReplaceAll(StringBuilder sb, String from, String into) {
        StringBuilder res = new StringBuilder(sb);
        int index = res.indexOf(from);
        while (index != -1) {
            res.replace(index, index + from.length(), into);
            index = res.indexOf(from, index + into.length());
        }
        return res;
    }

    private static String[] removeInvalidTokens(String str, int numSys) {
        if (!(numSys >= 2 && numSys <= 16)) {
            System.err.println("Numeral system is imposter");
            System.exit(-1);
        }
        if (numSys != 16) {
            String[] keys = wordToNum.keySet().toArray(new String[0]);
            String[] tokens = str.split(" ");
            ArrayList<String> validatedTokens = new ArrayList<String>();

            for (int i = 0; i < tokens.length; i++) {
                boolean isAbsent = true;
                for (int j = keys.length - 1; j > numSys - 1; j--) {
                    if (tokens[i].contains(keys[j])) {
                        isAbsent = false;
                        break;
                    }
                }
                if (isAbsent == true) {
                    validatedTokens.add(tokens[i]);
                }
            }
            return validatedTokens.toArray(new String[0]);
        } else {
            return str.split(" ");
        }
    }

    private static byte[] convertToByteArray(String[] tokens, int numSys) {
        ArrayList<Byte> validatedBytes = new ArrayList<Byte>();

        for (int i = 0; i < tokens.length; i++) {
            int number = 0;
            for (int j = 0; j < tokens[i].length(); j++) {
                int digit = numToDig.get(String.valueOf(tokens[i].charAt(j)));
                number += digit * (int) Math.pow(numSys, tokens[i].length() - 1 -j);
            }
            if (number <= 255) {
                validatedBytes.add((byte) number);
            }
        }

        if (validatedBytes.size() == 0) {
            System.err.println("All bytes are imposters");
            System.exit(-1);
        }
        byte[] result = new byte[validatedBytes.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = validatedBytes.get(i);
            }
            return result;
    }
}
