package decoder;
import java.util.Base64;

public class Decoder {
    public static String decode(String base64Code){
        byte[] decodedBytes = Base64.getDecoder().decode(base64Code);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
