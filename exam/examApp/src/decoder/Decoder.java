package decoder;
import java.util.Base64;

public class Decoder {
    public static String decode(String input) {
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        return new String(decodedBytes);
    }

    public static void main(String[] args) {
        System.out.println(decode("dGFzazEx"));
    }
}
