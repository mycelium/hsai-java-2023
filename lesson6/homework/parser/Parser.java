package lesson6.homework.parser;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Parser {
    private static final HashMap<String, Character> writtenToByte = new HashMap<>();
    private static final HashMap<Character, Integer> byteToDigit = new HashMap<>();
    private static final Integer[] numbers = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private static final Character[] byteNumbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String[] written = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать"};

    static {
        for (int i = 0; i < 16; i++) {
            writtenToByte.put(written[i], byteNumbers[i]);
            byteToDigit.put(byteNumbers[i], numbers[i]);
        }
    }

    private String[] thisContent;
    private Charset thisCharset;
    private int thisNumberSystem;

    public Parser(String[] contentArray, int numberSystem, String inputCharset) {
        thisContent = contentArray;
        thisNumberSystem = numberSystem;
        thisCharset = getCharset(inputCharset);
    }

    private Charset getCharset(String inputCharset) {
        Charset value = switch (inputCharset) {
            case "UTF-8" -> StandardCharsets.UTF_8;
            case "UTF-16" -> StandardCharsets.UTF_16;
            case "ASCII" -> StandardCharsets.US_ASCII;
            default -> throw new RuntimeException("Invalid charset!");
        };
        return value;
    }

    private String oneWordConvert(String oneWord) {
        String resString = "";
        String subString = "";
        for (int i = 0; i < oneWord.length(); i++) {
            subString += oneWord.charAt(i);
            if (writtenToByte.containsKey(subString)) {
                if (!(oneWord.startsWith("надцать", i + 1))) {
                    resString += writtenToByte.get(subString);
                    subString = "";
                }
            }
        }
        return resString;
    }

    public String[] writtenToDigitsConvert() {
        String[] resString = new String[thisContent.length];
        for (int i = 0; i < thisContent.length; i++) {
            resString[i] = oneWordConvert(thisContent[i]);
        }
        return resString;
    }

    private int getDigit(String num, int pos) {
        return byteToDigit.get(num.charAt(num.length() - 1 - pos));
    }

    private Optional<Byte> oneNumberConvert(String oneNumber, int thisNumberSystem) {
        int bufByte = 0;
        for (int i = 0; i < oneNumber.length(); i++) {
            if (getDigit(oneNumber, i) < thisNumberSystem) {
                bufByte += getDigit(oneNumber, i) * Math.pow(thisNumberSystem, i);
            } else break;
        }
        if (bufByte > 255) {
            return Optional.empty();
        }
        return Optional.of((byte) bufByte);
    }

    public byte[] toUserNumberSystemConvert(String[] content) {
        List<Byte> resArray = new ArrayList<>();
        for (int i = 0; i < content.length; i++) {
            Optional<Byte> res = oneNumberConvert(content[i], thisNumberSystem);
            res.ifPresent(resArray::add);
        }
        byte[] resByte = new byte[resArray.size()];
        for (int i = 0; i < resArray.size(); i++) {
            resByte[i] = resArray.get(i);
        }
        return resByte;
    }

    public String charsetConverter(byte[] number) {
        String printStr = new String(number, thisCharset);
        return printStr;
    }

}

