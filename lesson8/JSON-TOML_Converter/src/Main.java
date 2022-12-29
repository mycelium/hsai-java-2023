import converter.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("src/input.txt"));
        String json = new String(bytes);
        String toml = JsonToTomlConverter.convert(json);
        System.out.print(toml);
    }
}