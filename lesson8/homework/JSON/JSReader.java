package lesson8.homework.JSON;

import lesson8.homework.Converter.IOConverter;
import lesson8.homework.Converter.InterfaceConverter;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static lesson8.homework.Converter.IOConverter.replace;

public class JSReader implements InterfaceConverter {
    public Map<String, Object> read(String file) {
        Object map = new HashMap<String, Object>();
        try (FileReader fileReader = new FileReader(file)) {
            String content;
            if ((content = String.valueOf(fileReader)) != null) {
                replace(content);
                if (content.charAt(0) == '{') map = parseMap(content, 1, fileReader);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ((Map<String, Object>) map);
    }

    @Override
    public void write(String file, Object value, int t_count) {

    }

    public int putMap(String string, int i, Map<String, Object> map, FileReader fileReader) throws IOException {
        String key;
        if (string.charAt(i) == '\"') {
            int indexSl;
            indexSl = string.indexOf('\"', i + 2);
            key = string.substring(i + 1, indexSl);
            i = indexSl + 1;
            i++;
            switch (string.charAt(i)) {
                case '\"' -> {
                    i++;
                    int indexQu = string.indexOf('"', i);
                    map.put(key, string.substring(i, indexQu));
                    i = indexQu + 1;
                }
                case '[' -> {
                    i++;
                    map.put(key, parseArray(string, i, fileReader));
                }
                case '{' -> {
                    i++;
                    map.put(key, parseMap(string, i, fileReader));
                }
                default -> forDef(string, i, map, key);
            }
        }
        return i;
    }

    public int putArrayList(String string, int i, ArrayList<Object> arrayList, FileReader fileReader) throws IOException {
        switch (string.charAt(i)) {
            case '\"' -> {
                i++;
                int indexQu = string.indexOf('"', i);
                arrayList.add(string.substring(i, indexQu));
                i = indexQu + 1;
            }
            case '[' -> {
                i++;
                arrayList.add(parseArray(string, i, fileReader));
            }
            case '{' -> {
                i++;
                arrayList.add(parseMap(string, i, fileReader));
            }
            default -> forDef(string, i, arrayList);
        }
        return i;
    }

    int forDef(String string, int i, Map<String, Object> map, String key) {
        int end = string.indexOf(',', i + 1);
        if (end == -1) end = string.length();
        String value = string.substring(i, end);
        if (IOConverter.isNumeric(value)) {
            if (IOConverter.isInteger(value)) map.put(key, Integer.parseInt(value));
            else map.put(key, Double.parseDouble(value));
        } else if (value.equals("false")) map.put(key, false);
        else if (value.equals("true")) map.put(key, true);
        else if (value.equals("null")) map.put(key, null);
        i = end + 1;
        return (i);
    }

    int forDef(String string, int i, ArrayList<Object> arrayList) {
        int end = string.indexOf(',', i + 1);
        if (end == -1) end = string.length() - 1;
        String value = string.substring(i, end);
        if (IOConverter.isNumeric(value)) {
            if (IOConverter.isInteger(value)) arrayList.add(Integer.parseInt(value));
            else arrayList.add(Double.parseDouble(value));
        } else if (value.equals("false")) arrayList.add(false);
        else if (value.equals("true")) arrayList.add(true);
        else if (value.equals("null")) arrayList.add(null);
        i = end + 1;
        return (i);
    }

    public Map<String, Object> parseMap(String string, int index, FileReader fileReader) throws IOException {
        Map<String, Object> map = new HashMap<>();
        for (int i = index; i < string.length(); i++) {
            if (string.charAt(i) == '}') return map;
            i = putMap(string, i, map, fileReader);
        }
        while ((string = String.valueOf(fileReader)) != null) {
            replace(string);
            for (int i = index; i < string.length(); i++) {
                if (string.charAt(i) == '}') return map;
                i = putMap(string, i, map, fileReader);
            }
        }
        return map;
    }

    public ArrayList<Object> parseArray(String string, int index, FileReader fileReader) throws IOException {
        ArrayList<Object> array = new ArrayList<>();
        for (int i = index; i < string.length(); i++) {
            if (string.charAt(i) == ']') return array;
            i = putArrayList(string, i, array, fileReader);
            }
        while ((string = String.valueOf(fileReader)) != null) {
            replace(string);
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == ']') return array;
                i = putArrayList(string, i, array, fileReader);
            }
        }
        return array;
    }
}