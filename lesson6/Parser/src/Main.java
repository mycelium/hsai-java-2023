import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileContent = Files.readString(Path.of(args[0]));
        int base = Integer.parseInt(args[1]);
        Charset charset = Parser.parseCharset(args[2]);
        byte[] bytes = Parser.convertStrToBytes(fileContent, base, charset);
        
        Parser.printParsed(bytes, charset);
    }
}