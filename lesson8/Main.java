package lesson8;

import lesson8.parsers.*;
import lesson8.converters.*;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
            Map<String, Object> data = null;

            if (args[1].equals("JSON")) {
                JSONParser parserJSON = new JSONParser();
                data = parserJSON.read(args[0]);
            }
            else if (args[1].equals("TOML")) {
                TOMLParser parserTOML = new TOMLParser();
                data = parserTOML.read(args[0]);
            }
            else {
                System.err.println("Invalid format (first). Acceptable formats - \"JSON\", \"TOML\".");
            }

            if (args[3].equals("JSON")) {
                JSONConverter converterJSON = new JSONConverter();
                converterJSON.write(args[2], data);
            }
            else if (args[3].equals("TOML")) {
                TOMLConverter converterTOML = new TOMLConverter();
                converterTOML.write(args[2], data);
            }
            else {
                System.err.println("Invalid format (second). Acceptable formats - \"JSON\", \"TOML\".");
            }
    }
}
