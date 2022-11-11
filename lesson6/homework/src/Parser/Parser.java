package Parser;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Parser {

    private static HashMap<String, Character> ToDig = new HashMap<>();
    private static HashMap<Character, Integer> ToInt = new HashMap<>();
    private static String[] DigiWords = {
            "ноль",
            "один",
            "два",
            "три",
            "четыре",
            "пять",
            "шесть",
            "семь",
            "восемь",
            "девять",
            "десять",
            "одиннадцать",
            "двенадцать",
            "тринадцать",
            "четырнадцать",
            "пятнадцать",
    };
    private static Character[] byteNumbers = {
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
    };
    private static Integer[] intNumbers = new Integer[] {
            0,
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15,
    };

    static {
        ToDig = new HashMap<>();
        ToInt = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            ToDig.put(DigiWords[i], byteNumbers[i]);
            ToInt.put(byteNumbers[i], intNumbers[i]);
        }
    }

    public static String[] Num_To_Dig(String[] str) {
        String[] resStringArray = new String[str.length];
        for (int i = 0; i < str.length; i++) {
            String resString = new String();
            String bufString = new String();
            for (int j = 0; j < str[i].length(); j++) {
                bufString += str[i].charAt(j);
                if (ToDig.containsKey(bufString))
                    if (!(str[i].startsWith("на", j + 1))) {
                        resString += ToDig.get(bufString);
                        bufString = "";
                    }
            }
            resStringArray[i] = resString;
        }
        return resStringArray;
    }

    public static byte[] To_Byte_Arr(String[] str, int Degree) {
        byte[] OutByte = new byte[0];
        for (String first : str) {
            int bufInt = 0;
            for (int i = 0; i < first.length(); ++i) {
                if (ToInt.get(first.charAt(first.length() - 1 - i)) < Degree) {
                    bufInt += ToInt.get(first.charAt(first.length() - 1 - i)) *
                            Math.pow(Degree, i);
                } else
                    break;
            }
            if (bufInt < 256) {
                byte[] TmpArr = new byte[OutByte.length + 1];
                for (int i = 0; i < OutByte.length; ++i) {
                    TmpArr[i] = OutByte[i];
                }
                TmpArr[TmpArr.length - 1] = (byte) bufInt;
                OutByte = TmpArr;
            }
        }
        return OutByte;
    }

    public static Charset CharSet(String charset) throws Exception {
        switch (charset) {
            case "UTF-8":
                return StandardCharsets.UTF_8;
            case "UTF-16":
                return StandardCharsets.UTF_16;
            case "ASCII":
                return StandardCharsets.US_ASCII;
            default:
                throw new Exception("wrong type");
        }
    }
}
