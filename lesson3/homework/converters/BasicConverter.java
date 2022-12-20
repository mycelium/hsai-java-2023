package lesson3.homework.converters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lesson3.homework.exceptions.*;

public class BasicConverter {
    static Map<String, Convertable> converters = new HashMap<>();

    static {
        converters.put("json", new JSONConverter());
        converters.put("toml", new TOMLConverter());
    }

    public static Map<String, Object> read(String file, String format) throws InvalidFormatExeption {
        if (converters.containsKey(format)) {
            return converters.get(format).read(file);
        } else {
            throw new InvalidFormatExeption(format);
        }
    }

    public static void write(String file, String format, Map<String, Object> data) throws InvalidFormatExeption {
        if (converters.containsKey(format)) {
            converters.get(format).write(file, data, 0);
        } else {
            throw new InvalidFormatExeption(format);
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
