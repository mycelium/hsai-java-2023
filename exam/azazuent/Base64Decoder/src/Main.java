import decoder.Decoder;
import decoder.Tester;

import javax.imageio.IIOException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Tester.testBase64();
        Tester.testCaesar();
        Tester.solution();
    }
}