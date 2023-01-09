package lesson8.homework.TOML;

import lesson8.homework.Converter.IOConverter;
import lesson8.homework.Converter.InterfaceConverter;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static lesson8.homework.Converter.IOConverter.replace;

public class TOMLConverter implements InterfaceConverter {

    @Override
    public Map<String, Object> read(String file) {
        Map<String, Object> map = new HashMap<>();
        String content;
        try (FileReader fileReader = new FileReader(file)) {
            if ((content = String.valueOf(fileReader)) != null) {
                replace(content);
                if (!content.isEmpty()) putKeyValue(content, 0, map, fileReader);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return map;
    }

    @Override
    public void write(String file, Object value, int t_count) {

    }

    public int putKeyValue(String string, int i, Map<String, Object> map, FileReader fileReader) throws IOException {
        String key;
        int indexEnd;
        switch (string.charAt(i)) {
            case '\"' -> {
                indexEnd = string.indexOf('"', i + 1);
                i++;
                key = string.substring(i, indexEnd);
                indexEnd++;
            }
            case '[' -> {
                if (string.charAt(i + 1) == '[') parseArrayOfTable(string, i + 1, map, fileReader);
                else parseInlineTable(string, i + 1, map, fileReader);
                return string.length();
            }
            default -> {
                indexEnd = string.indexOf('=', i + 1);
                int indexPoint = string.substring(i + 1, indexEnd).indexOf(".") + 1 + i;
                if (indexPoint != i) {
                    key = string.substring(i, indexPoint);
                    if (map.containsKey(key)) i = putKeyValue(string, indexPoint + 1, (Map<String, Object>) map.get(key), fileReader);
                    else {
                        Map<String, Object> innerData = new HashMap<>();
                        i = putKeyValue(string, indexPoint + 1, innerData, fileReader);
                        map.put(key, innerData);
                    }
                    return i;
                }
                key = string.substring(i, indexEnd);
            }
        }
        i = indexEnd + 1;
        switch (string.charAt(indexEnd + 1)) {
            case '\"':
                int endString = string.indexOf('"', indexEnd + 2);
                map.put(
                        key,
                        string.substring(indexEnd + 2, endString));
                i = endString + 1;
                break;
            case '\'':
                endString = string.indexOf('\'', indexEnd + 2);
                map.put(
                        key,
                        string.substring(indexEnd + 2, endString));
                i = endString + 1;
                break;
            case '[':
                i++;
                map.put(key, parseArray(string, indexEnd + 2, fileReader));
                i = string.indexOf(']', i) + 1;
                break;
            case '{':
                i++;
                map.put(key, parseMap(string, indexEnd + 2, fileReader));
                i = string.indexOf('}', i) + 1;
                break;
            default:
                int end = string.indexOf(',', i + 1);
                int close = string.indexOf('}', i + 1);
                String value;
                if (close == -1 && end == -1) {
                    end = string.length();
                } else if ((end > close || end == -1) && close != -1) {
                    end = close;
                }
                value = string.substring(i, end);
                i = end;
                if (IOConverter.isNumeric(value)) {
                    if (IOConverter.isInteger(value)) {
                        map.put(key, Integer.parseInt(value));
                    } else {
                        map.put(key, Double.parseDouble(value));
                    }
                } else if (value.equals("false")) {
                    map.put(key, false);
                } else if (value.equals("true")) {
                    map.put(key, true);
                }
                break;
        }
        return i;
    }

    public int putValue(String string, int i, ArrayList<Object> data, FileReader fileReader) throws IOException {
        switch (string.charAt(i)) {
            case '\"' -> {
                i++;
                int endIndex = string.indexOf('"', i);
                data.add(string.substring(i, endIndex));
                i = endIndex + 1;
            }
            case '\'' -> {
                i++;
                int endIndex = string.indexOf('\'', i);
                data.add(string.substring(i, endIndex));
                i = endIndex + 1;
            }
            case '[' -> {
                i++;
                data.add(parseArray(string, i, fileReader));
                i = string.indexOf(']', i) + 1;
            }
            case '{' -> {
                i++;
                data.add(parseMap(string, i, fileReader));
                i = string.indexOf('}', i) + 1;
            }
            default -> {
                int end = string.indexOf(',', i + 1);
                int close = string.indexOf(']', i + 1);
                String value;
                if (close == -1 && end == -1) {
                    end = string.length();
                } else if ((end > close || end == -1) && close != -1) {
                    end = close;
                }
                value = string.substring(i, end);
                i = end;
                if (IOConverter.isNumeric(value)) {
                    if (IOConverter.isInteger(value)) data.add(Integer.parseInt(value));
                    else data.add(Double.parseDouble(value));
                } else if (value.equals("false")) data.add(false);
                else if (value.equals("true")) data.add(true);
                else if (value.equals("null")) data.add(null);
            }
        }
        return i;
    }


    public ArrayList<Object> parseArray(String string, int index, FileReader fileReader) throws IOException {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = index; i < string.length(); ) {
            if (string.charAt(i) == ']') return arrayList;
            if (string.charAt(i) != ',') i = putValue(string, i, arrayList, fileReader);
            else i++;
        }
        while ((string = String.valueOf(fileReader)) != null) {
            replace(string);
            for (int i = 0; i < string.length(); ) {
                if (string.charAt(i) == ']') return arrayList;
                if (string.charAt(i) != ',') i = putValue(string, i, arrayList, fileReader);
                else i++;
            }
        }
        return arrayList;
    }

    public Map<String, Object> parseMap(String string, int index, FileReader fileReader) throws IOException {
        Map<String, Object> map = new HashMap<>();
        for (int i = index; i < string.length(); ) {
            if (string.charAt(i) == '}') return map;
            if (string.charAt(i) != ',') i = putKeyValue(string, i, map, fileReader);
            else i++;
        }
        while ((string = String.valueOf(fileReader)) != null) {
            replace(string);
            for (int i = 0; i < string.length(); ) {
                if (string.charAt(i) == '}') return map;
                if (string.charAt(i) != ',') i = putKeyValue(string, i, map, fileReader);
                else i++;
            }
        }
        return map;
    }

    void parseInlineTable(String string, int i, Map<String, Object> map, FileReader fileReader) throws IOException {
        String key;
        Map<String, Object> stringObjectMap;
        int indexCl = string.indexOf(']', i);
        int indexP = string.indexOf('.', i);
        if (indexP != -1) {
            key = string.substring(i, indexP);
            stringObjectMap = new HashMap<>();
            parseInlineTable(string, indexP + 1, stringObjectMap, fileReader);
            map.put(key, stringObjectMap);
        } else {
            key = string.substring(i, indexCl);
            stringObjectMap = new HashMap<>();
            while ((string = String.valueOf(fileReader)) != null) {
                replace(string);
                if (string.isEmpty()) {
                    map.put(key, stringObjectMap);
                    return;
                }
                putKeyValue(string, 0, stringObjectMap, fileReader);
            }
            map.put(key, stringObjectMap);
        }
    }

    void parseArrayOfTable(String string, int i, Map<String, Object> map, FileReader fileReader) throws IOException {
        String key;
        ArrayList<Object> objectArrayList;
        Map<String, Object> stringObjectMap = new HashMap<>();
        int indexCLl = string.indexOf(']', i);
        key = string.substring(i + 1, indexCLl);
        while ((string = String.valueOf(fileReader)) != null) {
            replace(string);
            if (string.isEmpty()) break;
            putKeyValue(string, 0, stringObjectMap, fileReader);
        }
        if (map.containsKey(key)) {
            objectArrayList = (ArrayList<Object>) map.get(key);
            objectArrayList.add(stringObjectMap);
        } else {
            objectArrayList = new ArrayList<>();
            objectArrayList.add(stringObjectMap);
            map.put(key, objectArrayList);
        }
    }
}
