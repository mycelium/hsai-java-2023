package com.hsai.parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Parser {
    private static LinkedHashMap<String, String> inStringDigits = new LinkedHashMap<String, String>();
    private static LinkedHashMap<String, Integer> InDigits = new LinkedHashMap<String, Integer>();

    static {
        inStringDigits.put("ноль", "0");
        inStringDigits.put("один", "1");
        inStringDigits.put("два", "2");
        inStringDigits.put("три", "3");
        inStringDigits.put("четыре", "4");
        inStringDigits.put("пять", "5");
        inStringDigits.put("шесть", "6");
        inStringDigits.put("семь", "7");
        inStringDigits.put("восемь", "8");
        inStringDigits.put("девять", "9");
        inStringDigits.put("десять", "A");
        inStringDigits.put("одиннадцать", "B");
        inStringDigits.put("двенадцать", "C");
        inStringDigits.put("тринадцать", "D");
        inStringDigits.put("четырнадцать", "E");
        inStringDigits.put("пятнадцать", "F");

        InDigits.put("0", 0);
        InDigits.put("1", 1);
        InDigits.put("2", 2);
        InDigits.put("3", 3);
        InDigits.put("4", 4);
        InDigits.put("5", 5);
        InDigits.put("6", 6);
        InDigits.put("7", 7);
        InDigits.put("8", 8);
        InDigits.put("9", 9);
        InDigits.put("A", 10);
        InDigits.put("B", 11);
        InDigits.put("C", 12);
        InDigits.put("D", 13);
        InDigits.put("E", 14);
        InDigits.put("F", 15);
    }

    public static String parse(String str, int numberingSystem, Charset charset) {
        String codesStr = toCodes(str);
        String[] values = removeInvalidValues(codesStr, numberingSystem);
        byte[] bytes = ToByteArray(values, numberingSystem);
        return new String(bytes, charset);
    }
    
    public static Charset getEncoding(String encoding) {
        switch (encoding) {
            case "ASCII":
                return StandardCharsets.US_ASCII;
            case "UTF-8":
                return StandardCharsets.UTF_8;
            case "UTF-16":
                return StandardCharsets.UTF_16;
            default:
                System.err.println("Encoding is incorrect.");
                System.exit(-1);
                return null;
        }
    }

    private static String toCodes(String str) {
        String[] keys = inStringDigits.keySet().toArray(new String[0]);
        StringBuilder result = new StringBuilder(str);

        for (int i = keys.length - 1; i >= 0; i--) {
            result = replaceAll(result, keys[i], inStringDigits.get(keys[i]));
        }

        return result.toString();
    }

    private static StringBuilder replaceAll(StringBuilder sb, String s1, String s2) {
        StringBuilder result = new StringBuilder(sb);
        int ind = result.indexOf(s1);

        while (ind != -1) {
            result.replace(ind, ind + s1.length(), s2);
            ind = result.indexOf(s1, ind + s2.length());
        }

        return result;
    }

    private static String[] removeInvalidValues(String str, int numberingSystem) {
        if (numberingSystem < 2 || numberingSystem > 16) {
            System.err.println("The numbering system is incorrect.");
            System.exit(-1);
        }

        if (numberingSystem != 16) {
            String[] keys = inStringDigits.keySet().toArray(new String[0]);
            String[] values = str.split(" ");
            ArrayList<String> trueValues = new ArrayList<String>();

            for (int i = 0; i < values.length; i++) {
                boolean trueValueFlag = true;
                for (int j = keys.length - 1; j > numberingSystem - 1; j--) {
                    if (values[i].contains(keys[j])) {
                        trueValueFlag = false;
                        break;
                    }
                }
                if (trueValueFlag == true) {
                    trueValues.add(values[i]);
                }
            }
            return trueValues.toArray(new String[0]);
        } 
        else {
            return str.split(" ");
        }
    }

    private static byte[] ToByteArray(String[] values, int numberingSystem) {
        ArrayList<Byte> trueBytes = new ArrayList<Byte>();

        for (int i = 0; i < values.length; i++) {
            int number = 0;
            for (int j = 0; j < values[i].length(); j++) {
                int digit = InDigits.get(String.valueOf(values[i].charAt(j)));
                number += digit * Math.pow(numberingSystem, values[i].length() - j - 1);
            }
            if (number <= 255) {
                trueBytes.add((byte) number);
            }
        }

        byte[] result = new byte[trueBytes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = trueBytes.get(i);
        }
        return result;
    }
}