package lesson6.homework;

import lesson6.homework.src.Decoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {

        String fileString = Files.readString(Path.of(args[0]));
        String[] splitString = fileString.split(" ");

        Decoder decoder = new Decoder(Integer.parseInt(args[1]), splitString, args[2]);
        decoder.printOut();
    }
}
