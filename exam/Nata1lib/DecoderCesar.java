package exam.Nata1lib;

import java.nio.charset.StandardCharsets;

public class DecoderCesar {
    String str = Files.readString(Path.of("exam/tasks/task9"));
    byte[] bytes1 = str.getBytes(StandardCharsets.UTF_8);
    char[] chars1 = new String(bytes1, StandardCharsets.UTF_8).toCharArray();
    char[] chars2 = new char[chars1.length];
for(
    String str1 = new String(chars2);
    i<chars1.length;i++)
    int i = 0;

    {
        chars2[i] = (char) (chars1[i] - 4523167);
    }
System.out.println(str1);
}
