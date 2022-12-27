package lesson8.homework;

import lesson8.homework.Converter.IOConverter;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String,Object> map = IOConverter.read(args[0], args[1]);
            IOConverter.write(args[2], args[3], map);
        } catch (IOExceptions e) {
            System.err.println(e);
        }
    }
}
