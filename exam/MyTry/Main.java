import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        var result = Base64.getDecoder().decode("dGFzazEz");
        String decodedStr = new String(result);
        System.out.println(decodedStr);

        Path path = Paths.get("src/task13");
        String read = Files.readAllLines(path).get(0);

        char[] message = read.toCharArray();
        char[] eMessage = new char[message.length];
        int shift = 2534167; //0x26AB17

        //encrypting message
        for(int i = 0; i <= message.length; ++i)
        {
            eMessage[i] = (char) (message[i] - shift);
            System.out.println(eMessage);
        }
    }
}