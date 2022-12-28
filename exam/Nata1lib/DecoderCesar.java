package exam.Nata1lib;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DecoderCesar {
    public static void main(String[] args) throws Exception {
        String str = Files.readString(Path.of("exam/tasks/task9"));
        byte[] bytes1 = str.getBytes("UTF-8");
        char[] chars1 = new String(bytes1, "UTF-8").toCharArray();
        char[] chars2 = new char[chars1.length];
        for (int i = 0; i < chars1.length; i++) {
            chars2[i] = (char) (chars1[i] - 4523167);
        }
        String str1 = new String(chars2);
        System.out.println(str1);
    }
}
