package exam.Nata1lib;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Decoder {
    public static void main(String[] args) throws InterruptedException {
        try {
            String firstStr = "dGFzazk=";
            byte[] bytes = firstStr.getBytes("UTF-8");
            byte[] decoded = Base64.getDecoder().decode(bytes);
            String decodedStr = new String(decoded, StandardCharsets.UTF_8);
            System.out.println(decodedStr);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}