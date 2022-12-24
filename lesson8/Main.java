package lesson8;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] s = {"lesson8/json/example.json","JSON","lesson8/json/result.toml","TOML"};

        Builder.builder(s[0],s[1],s[2],s[3]);
    }
}
