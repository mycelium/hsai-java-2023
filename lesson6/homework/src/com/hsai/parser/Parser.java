package com.hsai.parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.hsai.parser.exceptions.*;

public class Parser {
    // LinkedHashMap is used in order to contain values by the order they are
    // inserted and preserving this order after using numbers.keySet().toArray().
    // This is necessary for iterating this array in reverse order for avoiding
    // problem of replacing, for example, "три" in "тринадцать".
    // Iterating from the end, all instances of "тринадцать" will have been
    // replaced before "три" is replaced.
    private static LinkedHashMap<String, String> naturalToCodes = new LinkedHashMap<String, String>();
    private static LinkedHashMap<String, Integer> codesToNumbers = new LinkedHashMap<String, Integer>();

    static {
        naturalToCodes.put("ноль", "0");
        naturalToCodes.put("один", "1");
        naturalToCodes.put("два", "2");
        naturalToCodes.put("три", "3");
        naturalToCodes.put("четыре", "4");
        naturalToCodes.put("пять", "5");
        naturalToCodes.put("шесть", "6");
        naturalToCodes.put("семь", "7");
        naturalToCodes.put("восемь", "8");
        naturalToCodes.put("девять", "9");
        naturalToCodes.put("десять", "A");
        naturalToCodes.put("одиннадцать", "B");
        naturalToCodes.put("двенадцать", "C");
        naturalToCodes.put("тринадцать", "D");
        naturalToCodes.put("четырнадцать", "E");
        naturalToCodes.put("пятнадцать", "F");

        codesToNumbers.put("0", 0);
        codesToNumbers.put("1", 1);
        codesToNumbers.put("2", 2);
        codesToNumbers.put("3", 3);
        codesToNumbers.put("4", 4);
        codesToNumbers.put("5", 5);
        codesToNumbers.put("6", 6);
        codesToNumbers.put("7", 7);
        codesToNumbers.put("8", 8);
        codesToNumbers.put("9", 9);
        codesToNumbers.put("A", 10);
        codesToNumbers.put("B", 11);
        codesToNumbers.put("C", 12);
        codesToNumbers.put("D", 13);
        codesToNumbers.put("E", 14);
        codesToNumbers.put("F", 15);
    }

    public static Charset getCharset(String charset) throws InvalidCharsetException {
        switch (charset) {
            case "ASCII":
                return StandardCharsets.US_ASCII;
            case "UTF-8":
                return StandardCharsets.UTF_8;
            case "UTF-16":
                return StandardCharsets.UTF_16;
            default:
                throw new InvalidCharsetException(charset);
        }
    }

    public static String parse(String str, int numeralSystem, Charset charset)
            throws InvalidNumeralSystemException, ResultIsEmptyException {
        String codesStr = toCodes(str);
        String[] tokens = removeInvalidTokens(codesStr, numeralSystem);
        byte[] bytes = convertToByteArray(tokens, numeralSystem);
        return new String(bytes, charset);
    }

    private static String toCodes(String str) {
        String[] keys = naturalToCodes.keySet().toArray(new String[0]);
        StringBuilder result = new StringBuilder(str);

        for (int i = keys.length - 1; i >= 0; i--) {
            result = sbReplaceAll(result, keys[i], naturalToCodes.get(keys[i]));
        }

        return result.toString();
    }

    // Why is immutable String does have this method, but mutable StringBuilder and
    // StringBuffer do not???
    private static StringBuilder sbReplaceAll(StringBuilder sb, String from, String to) {
        StringBuilder result = new StringBuilder(sb);
        int index = result.indexOf(from);

        while (index != -1) {
            result.replace(index, index + from.length(), to);
            index = result.indexOf(from, index + to.length());
        }

        return result;
    }

    // Removes tokens which are not valid for given numeral system.
    private static String[] removeInvalidTokens(String str, int numeralSystem)
            throws InvalidNumeralSystemException, ResultIsEmptyException {
        if (numeralSystem >= 2 && numeralSystem <= 16) {
            if (numeralSystem != 16) {
                String[] keys = naturalToCodes.keySet().toArray(new String[0]);
                String[] tokens = str.split(" ");
                ArrayList<String> validatedTokens = new ArrayList<String>();

                for (int i = 0; i < tokens.length; i++) {
                    boolean isAbsent = true;
                    for (int j = keys.length - 1; j > numeralSystem - 1; j--) {
                        if (tokens[i].contains(keys[j])) {
                            isAbsent = false;
                            break;
                        }
                    }
                    if (isAbsent == true) {
                        validatedTokens.add(tokens[i]);
                    }
                }

                if (validatedTokens.size() != 0) {
                    return validatedTokens.toArray(new String[0]);
                } else {
                    throw new ResultIsEmptyException(str, numeralSystem);
                }
            } else {
                return str.split(" ");
            }
        } else {
            throw new InvalidNumeralSystemException(numeralSystem);
        }
    }

    private static byte[] convertToByteArray(String[] tokens, int numeralSystem) throws ResultIsEmptyException {
        ArrayList<Byte> validatedBytes = new ArrayList<Byte>();

        for (int i = 0; i < tokens.length; i++) {
            int number = 0;
            for (int j = tokens[i].length() - 1; j >= 0; j--) {
                int digit = Character.getNumericValue(tokens[i].charAt(j));
                number += digit * (int) Math.pow(digit, j);
            }
            if (number <= 255) {
                validatedBytes.add((byte) number);
            }
        }

        if (validatedBytes.size() != 0) {
            byte[] result = new byte[validatedBytes.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = validatedBytes.get(i);
            }
            return result;
        } else {
            throw new ResultIsEmptyException(tokens, numeralSystem);
        }
    }
}
