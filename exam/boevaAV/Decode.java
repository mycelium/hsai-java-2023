package exam.boevaAV;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class Decode {
    public static void Main(String[] args) throws InterruptedException, IOException {
        String firstStr = "dGFzazl=";
        byte[] bytes = firstStr.getBytes("UTF-8");
        byte[] decoded = Base64.getDecoder().decode(bytes);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        System.out.println(decodedStr);

        String str = Files.readString(Path.of("exam/tasks/task9"));
        byte[] bytes1 = str.getBytes("UTF-8");
        char[] chars1 = new String(bytes1, "UTF-8").toCharArray();
        char[] chars2 = new char[chars1.length];
        for(int i = 0; i< chars1.length; i++){
            chars2[i] = (char) (chars1[i]-4523167);
        }
        String str1 = new String(chars2);
        System.out.println(str1);
    }
}
