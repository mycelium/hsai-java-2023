package decoder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tester {
    public static void solution() throws IOException {
        String line = Files.readString(Paths.get("src/task17"), StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder(line);
        for (int i = 0; i<sb.length(); i++)
            sb.setCharAt(i, (char)(sb.charAt(i)-3246175));
        System.out.println(sb.toString());
    }

    public static void testBase64(){
        System.out.println("Base64 testing:");
        System.out.println(Decoder.decodeBase64("dGFzazE3"));
        System.out.println(Decoder.decodeBase64("aGVsbG8gd29ybGQ="));
    }

    public static void testCaesar(){
        int key = 3246175;
        System.out.println("Caesar testing:");
        System.out.println("Encoding \"helloworld\"");
        StringBuilder sb = new StringBuilder("helloworld");
        for (int i = 0; i<sb.length(); i++)
            sb.setCharAt(i, (char)(sb.charAt(i)+key));
        System.out.println(sb.toString());
        for (int i = 0; i<sb.length(); i++)
            sb.setCharAt(i, (char)(sb.charAt(i)-key));
        System.out.println(sb.toString());

        System.out.println("Encoding \"я работаю\"");
        sb = new StringBuilder("я работаю");
        for (int i = 0; i<sb.length(); i++)
            sb.setCharAt(i, (char)(sb.charAt(i)+key));
        System.out.println(Decoder.decodeCaesar(sb.toString(), key));
        for (int i = 0; i<sb.length(); i++)
            sb.setCharAt(i, (char)(sb.charAt(i)-key));
        System.out.println(sb.toString());

        System.out.println("Encoding \"i`m РАБОТАЮ\"");
        sb = new StringBuilder("i`m РАБОТАЮ");
        for (int i = 0; i<sb.length(); i++)
            sb.setCharAt(i, (char)(sb.charAt(i)+key));
        System.out.println(sb.toString());
        for (int i = 0; i<sb.length(); i++)
            sb.setCharAt(i, (char)(sb.charAt(i)-key));
        System.out.println(sb.toString());
    }
}
