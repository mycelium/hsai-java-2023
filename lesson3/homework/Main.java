package lesson3.homework;

import lesson3.homework.exceptions.InvalidFormatExeption;
import lesson3.homework.converters.BasicConverter;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String,Object> data = BasicConverter.read(args[0], args[1]);
            BasicConverter.write(args[2], args[3], data);
        } catch (InvalidFormatExeption e) {
            System.err.println(e);
        }
    }
}
