package lesson6.homework.src;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Decoder {
    static private final HashMap<String, Character> numbers;
    static private final HashMap<Character, Integer> byteAndNumbers;
    static private final String[] writtenNumbers = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать"};
    static private final Character[] byteNumbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    static private final Integer[] intNumbers = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    static {
        numbers = new HashMap<>();
        byteAndNumbers = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            numbers.put(writtenNumbers[i], byteNumbers[i]);
            byteAndNumbers.put(byteNumbers[i], intNumbers[i]);
        }
    }

    private final int base;
    private final String[] originalNumbers;
    private final String encoding;

    public Decoder(int b, String[] num, String enc) {
        base = b;
        originalNumbers = num;
        encoding = enc;
    }

    private String extractBytes(String stringNumber) {

        String number = "";

        while (!stringNumber.equals("")) {

            for (int i = 3; i <= 12; i++) {
                String subStr = stringNumber.substring(0, i);
                if (subStr.equals("один") || subStr.equals("три")) {
                    String problemString = "надцать";
                    if (stringNumber.length() >= subStr.length() + problemString.length()) {
                        if (stringNumber.substring(0, subStr.length() + problemString.length()).equals(subStr + problemString))
                            subStr = subStr + problemString;
                    }
                }

                if (numbers.containsKey(subStr)) {
                    stringNumber = stringNumber.substring(subStr.length());
                    number = number.concat(numbers.get(subStr).toString());
                    break;
                }
            }

        }
        return number;
    }

    private Optional<Byte> baseNumberFromBytes(String stringNumber) {
        int number = 0;
        for (int i = stringNumber.length() - 1, j = 0; i >= 0 && j < stringNumber.length(); i--, j++) {
            int currentNumber = byteAndNumbers.get(stringNumber.charAt(i));
            if (currentNumber >= base) return Optional.empty();
            number += currentNumber * (int) Math.pow(base, j);
        }
        if (number > 255) return Optional.empty();
        else return Optional.of((byte) number);
    }

    public void printOut() {
        List<Byte> array = new ArrayList<>();

        for (String originalNumber : originalNumbers) {
            Optional<Byte> result = baseNumberFromBytes(extractBytes(originalNumber));
            result.ifPresent(array::add);
        }
        byte[] bytesArray = new byte[array.size()];

        for (int i = 0; i < array.size(); i++) {
            bytesArray[i] = array.get(i);
        }
        String stringToPrint = new String(bytesArray, Charset.forName(encoding));
        System.out.println(stringToPrint);
    }
}
