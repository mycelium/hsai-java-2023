package lesson8.homework.Converter;

import java.util.HashMap;
import java.util.Map;

import lesson8.homework.IOExceptions;
import lesson8.homework.JSON.JSReader;
import lesson8.homework.TOML.TOMLConverter;

public class IOConverter {
    static Map<String, InterfaceConverter> staticMap = new HashMap<>();

    static {
        staticMap.put("JSON", new JSReader());
        staticMap.put("TOML", new TOMLConverter());
    }

    public static Map<String, Object> read(String file, String format) throws IOExceptions {
        if (staticMap.containsKey(format)) return staticMap.get(format).read(file);
        else throw new IOExceptions(format);
    }

    public static void write(String file, String string, Map<String, Object> map) throws IOExceptions {
        if (staticMap.containsKey(string)) staticMap.get(string).write(file, map, 0);
        else throw new IOExceptions(string);
    }

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void replace(String string){
        string = string.replace(" ", "");
        string = string.replace("\t", "");
        string = string.replace("\n", "");
    }
}