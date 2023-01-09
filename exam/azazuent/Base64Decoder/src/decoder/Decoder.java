package decoder;
import java.util.Base64;

public class Decoder {
    public static String decodeBase64(String base64Code){
        byte[] decodedBytes = Base64.getDecoder().decode(base64Code);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    public static String decodeCaesar(String cipherText, int shiftKey) {
        StringBuilder sb = new StringBuilder(cipherText);
        for (int i = 0; i<sb.length(); i++)
            sb.setCharAt(i, (char)(sb.charAt(i)-shiftKey));
        return sb.toString();
    }
}
