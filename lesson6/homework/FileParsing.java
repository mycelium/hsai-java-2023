package lesson6.homework;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static lesson6.homework.Main.copyArray;

public class FileParsing {
    static private final HashMap<String, String> textByte = new HashMap<>();
    static private final HashMap<Character, Integer> byteNumbers = new HashMap<>();
    static private final String[] textNumbers = {"ноль","один", "два","три","четыре","пять","шесть","семь","восемь","девять","десять","одиннадцать","двеннадцать","тринадцать","четырнадцать","пятнадцать"};
    static private final String[] Numbers = {"0","1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    static private final Character[] Bytes = {'0','1', '2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    static {
        for(int i = 0; i<16; i++){
            textByte.put(textNumbers[i],Numbers[i]);
            byteNumbers.put(Bytes[i], i);
        }
    }
    public static Charset textEncoding(String encoding){
        if (encoding.equals("ASCII")) return StandardCharsets.US_ASCII;
        else if (encoding.equals("UTF-8")) return StandardCharsets.UTF_8;
        else if (encoding.equals("UTF-16")) return StandardCharsets.UTF_16;
        else return StandardCharsets.US_ASCII;
    }
    public static String[] extractBytes(String[] array){
        String[] resArray = new String[array.length];
        for(int i = 0; i< array.length; i++){
            StringBuilder res = new StringBuilder();
            String tmp = "";
            for(int j = 0; j< array[i].length(); j++){
                tmp += array[i].charAt(j);
                if(textByte.containsKey(tmp)){
                    if(!(array[i].endsWith("надцать"))){
                        res.append(textByte.get(tmp));
                        tmp = "";
                    }
                }
            }
            resArray[i] = res.toString();
        }
        return resArray;
    }
    public static byte[]  baseNumberFromBytes(String[] array, int numberSystem){
        int max = 256;
        byte[] resArray = new byte[0];
        for(String it:array){
            int tmp = 0;
            for(int i=0;i<it.length();i++){
                char key = it.charAt(it.length() - i - 1);
                if(byteNumbers.get(key) < numberSystem){
                    tmp += byteNumbers.get(key) * Math.pow(numberSystem,i);
                } else break;
            }
            if(tmp<max){
                byte[] newArray = new byte[resArray.length + 1];
                copyArray(resArray, newArray);
                newArray[newArray.length-1] = (byte) tmp;
                resArray = newArray;
            }
        }
        return resArray;
    }
}