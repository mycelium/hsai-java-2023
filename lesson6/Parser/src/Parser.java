import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;


public class Parser {
    public static LinkedHashMap<String, String> numbers;

    static {
        numbers = new LinkedHashMap<>(16);
        numbers.put("ноль", "0");
        numbers.put("один", "1");
        numbers.put("два", "2");
        numbers.put("три", "3");
        numbers.put("четыре", "4");
        numbers.put("пять", "5");
        numbers.put("шесть", "6");
        numbers.put("семь", "7");
        numbers.put("восемь", "8");
        numbers.put("девять", "9");
        numbers.put("деcять", "A");
        numbers.put("одиннадцать", "B");
        numbers.put("двенадцать", "C");
        numbers.put("тринадцать", "D");
        numbers.put("четырнадцать", "E");
        numbers.put("пятнадцать", "F");
    }

    /**
     * Method parses a string that represent number in verbal form, written
     * in the certain number system, and returns a 1 or 2 byte representation
     *
     * @param byteWordString -- string representation of bytes ('двапять одиндвенадцать ...")
     * @param radix          - base of number system: 2,3,4,...,16
     * @param cs             - encoding charset
     * @return array of bytes which consists of bytes that represent the number
     * if number is correctly encoded in chosen numerical system, nulls otherwise.
     */
    public static Byte[] getIfCorrect(String byteWordString, int radix, Charset cs) {
        String[] keys = numbers.keySet().toArray(new String[0]);
        for (int i = radix - 1; i >= 0; i--) {
            if (byteWordString.contains(keys[i])) {
                byteWordString = byteWordString.replaceAll(keys[i], numbers.get(keys[i]));
            }
        }
        int val = Integer.parseInt(byteWordString, radix);
        Byte[] result = new Byte[]{null, null};

        // for UTF-16 encoding we should save 2 bytes, besides the
        // case if the value fits in 1 byte
        if (cs == StandardCharsets.UTF_16) {
            if (0 <= val && val <= 255) {
                result[0] = (byte) val;
                result[1] = 0;
            } else if (256 <= val && val <= 65535) {
                result[0] = (byte) ((val >> 8) & 0xFF);
                result[1] = (byte) (val & 0xFF);
            }
        } else if (0 <= val && val <= 255) {
            result[0] = (byte) val;
        }

        return result;
    }

    /**
     * Method parses string which represent sequence of bytes in verbal form, written
     * in the certain number system, and returns the list of bytes
     *
     * @param bytesInWords - string representation of bytes ('двапять одиндвенадцать ...")
     * @param radix        - base of number system: 2,3,4,...,16
     * @return Array of bytes
     */
    public static byte[] convertStrToBytes(String bytesInWords, int radix, Charset cs) {
        ArrayList<Byte> bytes = new ArrayList<>();
        for (String byteWord : bytesInWords.split(" ")) {
            Byte[] wordBytes = getIfCorrect(byteWord, radix, cs);
            for (Byte b : wordBytes) {
                if (!Objects.isNull(b)) {
                    bytes.add(b);
                }
            }
        }

        byte[] result = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            result[i] = bytes.get(i).byteValue();
        }

        return result;
    }

    public static Charset parseCharset(String charset) {
        return switch (charset) {
            case "ASCII" -> StandardCharsets.US_ASCII;
            case "UTF-8" -> StandardCharsets.UTF_8;
            default -> StandardCharsets.UTF_16;
        };
    }

    public static void printParsed(byte[] bytes, Charset cs) {
        System.out.println(new String(bytes, cs));
    }
}