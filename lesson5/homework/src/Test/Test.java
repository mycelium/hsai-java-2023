package lesson5.homework.src.Test;

import lesson5.homework.src.Converter;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<String, Integer> testTokensDecimal = new HashMap<>();
        {
            testTokensDecimal.put("дватрипять", 235);
            testTokensDecimal.put("одиннольноль", 100);
            testTokensDecimal.put("нольодинноль", 10);
            testTokensDecimal.put("одиннольтри", 103);
            testTokensDecimal.put("одинноль", 10);
            testTokensDecimal.put("ноль", 0);
            testTokensDecimal.put("пять", 5);
        }
        System.out.println("Tests for Decimal number system:" + '\n');
        for (HashMap.Entry<String, Integer> entry : testTokensDecimal.entrySet()) {
            if (Converter.stringToByte(entry.getKey(), 10) == entry.getValue()) {
                System.out.println("Byte " + entry.getKey() + " transformed correctly" + '\n');
            } else {
                System.out.println("Byte " + entry.getKey() + " transformed uncorrectly" + '\n');
            }
        }
        HashMap<String, Integer> testTokensHex = new HashMap<>();
        {
            testTokensHex.put("четырнадцатьноль", 0xE0);
            testTokensHex.put("одиннольноль", 0x100);
            testTokensHex.put("одиндесять", 0x1A);
            testTokensHex.put("пятнадцатьдва", 0xF2);
            testTokensHex.put("одинодиннадцать", 0x1B);
            testTokensHex.put("четырнадцать", 0xE);
            testTokensHex.put("ноль", 0);
        }
        System.out.println("Tests for Hex number system:" + '\n');
        for (HashMap.Entry<String, Integer> entry : testTokensHex.entrySet()) {
            if (Converter.stringToByte(entry.getKey(), 16) == entry.getValue()) {
                System.out.println("Byte " + entry.getKey() + " transformed correctly" + '\n');
            } else {
                System.out.println("Byte " + entry.getKey() + " transformed uncorrectly" + '\n');
            }
        }
    }
}
