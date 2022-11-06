package lesson6.homework.StringParser.src;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.Charset;

public class StringConverter {
    static private final HashMap<String, Integer> nums = new HashMap<>();
    static private final String[] strNums = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать"};
    static private final Integer[] intNums = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    static {
        for(int i = 0; i < intNums.length; i++) {
            nums.put(strNums[i], intNums[i]);
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
            int digit = Integer.parseInt(String.valueOf(byteStr.charAt(byteStr.length() - i - 1)));
            if (digit >= base) return -1;

            corrNum += digit * (int) Math.pow(base, i);
        }

        if (corrNum > 255) return -1;

        return corrNum;
    }
    private int strToByte(String str) {
        String strNumber = "";
        String tempStr = "";

        for(Character symb : str.toCharArray()) {
            tempStr = tempStr.concat(symb.toString());

            if (tempStr.equals("один") || tempStr.equals("три")) {
                String longStr = "надцать";
                if (str.length() >= tempStr.length() + longStr.length()) {
                    if (str.substring(0, tempStr.length() + longStr.length()).equals(tempStr + longStr))
                        tempStr = tempStr + longStr;
                }
            }
            if (nums.containsKey(tempStr)) {
                strNumber = strNumber.concat(nums.get(tempStr).toString());
                tempStr = "";
            }
        }

        return correctBaseNumber(strNumber);
    }

    public List<Byte> convertStrToByteArr() {
        List<Byte> convertedNumbers = new ArrayList();

        for (String currString : stringFromFile) {
            int num = strToByte(currString);
            if(num != -1) convertedNumbers.add((byte)num);
        };

        return convertedNumbers;
    }

    public void printBytesArr(List<Byte> arr) {
        byte[] bytesArray = new byte[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            bytesArray[i] = arr.get(i);
        }

        String stringToPrint = new String(bytesArray, Charset.forName(encoding));
        System.out.println(stringToPrint);
    }
}
