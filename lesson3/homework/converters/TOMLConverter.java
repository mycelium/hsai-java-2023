package lesson3.homework.converters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TOMLConverter implements Convertable {
    public Map<String, Object> read(String file) {
        Map<String, Object> data = new HashMap<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(file))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                s = s.replace(" ", "");
                s = s.replace("\t", "");
                s = s.replace("\n", "");
                if (!s.isEmpty())
                    putKeyValue(s, 0, data, bufferedReader);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return data;
    }

    public int putKeyValue(String s, int i, Map<String, Object> data, BufferedReader bw) throws IOException {
        String key = null;
        int indexEnd;
        switch (s.charAt(i)) {
            case '\"':
                indexEnd = s.indexOf('"', i + 1);
                i++;
                key = s.substring(i, indexEnd);
                indexEnd++;
                break;
            case '[':
                if (s.charAt(i + 1) == '[') {
                    parseArrayOfTable(s, i + 1, data, bw);
                    return s.length();
                } else {
                    parseInlineTable(s, i + 1, data, bw);
                    return s.length();
                }

            default:
                indexEnd = s.indexOf('=', i + 1);
                int indexPoint = s.substring(i + 1, indexEnd).indexOf(".") + 1 + i;
                if (indexPoint != -1 && indexPoint != i) {
                    key = s.substring(i, indexPoint);
                    if (data.containsKey(key)) {
                        i = putKeyValue(s, indexPoint + 1, (Map<String, Object>) data.get(key), bw);
                    } else {
                        Map<String, Object> innerData = new HashMap<>();
                        i = putKeyValue(s, indexPoint + 1, innerData, bw);
                        data.put(key, innerData);
                    }
                    return i;
                }
                if (indexEnd == -1) {
                    //throw нет = или пустая строка
                }
                key = s.substring(i, indexEnd);
                break;
        }
        i = indexEnd + 1;
        switch (s.charAt(indexEnd + 1)) {
            case '\"':
                int endString = s.indexOf('"', indexEnd + 2);
                data.put(
                        key,
                        new String(s.substring(indexEnd + 2, endString)));
                i = endString + 1;
                break;
            case '\'':
                endString = s.indexOf('\'', indexEnd + 2);
                data.put(
                        key,
                        new String(s.substring(indexEnd + 2, endString)));
                i = endString + 1;
                break;
            case '[':
                i++;
                data.put(key, parseArray(s, indexEnd + 2, bw));
                i = s.indexOf(']', i) + 1;
                break;
            case '{':
                i++;
                data.put(key, parseMap(s, indexEnd + 2, bw));
                i = s.indexOf('}', i) + 1;
                break;
            default:
                int end = s.indexOf(',', i + 1);
                int close = s.indexOf('}', i + 1);
                String value;
                if (close == -1 && end == -1) {
                    end = s.length();
                } else if ((end > close || end == -1) && close != -1) {
                    end = close;
                }
                value = s.substring(i, end);
                i = end;
                if (BasicConverter.isNumeric(value)) {
                    if (BasicConverter.isInteger(value)) {
                        data.put(key, Integer.parseInt(value));
                    } else {
                        data.put(key, Double.parseDouble(value));
                    }
                } else if (value.equals("false")) {
                    data.put(key, false);
                } else if (value.equals("true")) {
                    data.put(key, true);
                }
                break;
        }
        return i;
    }

    public int putValue(String s, int i, ArrayList<Object> data, BufferedReader bufferedReader) throws IOException {
        switch (s.charAt(i)) {
            case '\"':
                i++;
                int endIndex = s.indexOf('"', i);
                data.add(new String(s.substring(i, endIndex)));
                i = endIndex + 1;
                break;
            case '\'':
                i++;
                endIndex = s.indexOf('\'', i);
                data.add(new String(s.substring(i, endIndex)));
                i = endIndex + 1;
                break;
            case '[':
                i++;
                data.add(parseArray(s, i, bufferedReader));
                i = s.indexOf(']', i) + 1;
                break;
            case '{':
                i++;
                data.add(parseMap(s, i, bufferedReader));
                i = s.indexOf('}', i) + 1;
                break;
            default:
                int end = s.indexOf(',', i + 1);
                int close = s.indexOf(']', i + 1);
                String value;
                if (close == -1 && end == -1) {
                    end = s.length();
                } else if ((end > close || end == -1) && close != -1) {
                    end = close;
                }
                value = s.substring(i, end);
                i = end;
                if (BasicConverter.isNumeric(value)) {
                    if (BasicConverter.isInteger(value)) {
                        data.add(Integer.parseInt(value));
                    } else {
                        data.add(Double.parseDouble(value));
                    }
                } else if (value.equals("false")) {
                    data.add(false);
                } else if (value.equals("true")) {
                    data.add(true);
                } else if (value.equals("null")) {
                    data.add(null);
                }
                break;
        }
        return i;
    }


    public ArrayList<Object> parseArray(String s, int index, BufferedReader bufferedReader) throws IOException {
        ArrayList<Object> data = new ArrayList<>();
        int line = 1;
        for (int i = index; i < s.length(); ) {
            if (s.charAt(i) == ']') {
                return data;
            }
            if (s.charAt(i) != ',') {
                i = putValue(s, i, data, bufferedReader);
            } else {
                i++;
            }
        }

        while ((s = bufferedReader.readLine()) != null) {
            s = s.replace(" ", "");
            s = s.replace("\t", "");
            s = s.replace("\n", "");
            for (int i = 0; i < s.length(); ) {
                if (s.charAt(i) == ']') {
                    return data;
                }
                if (s.charAt(i) != ',') {
                    i = putValue(s, i, data, bufferedReader);
                } else {
                    i++;
                }
            }
        }
        return data;
    }

    public Map<String, Object> parseMap(String s, int index, BufferedReader bufferedReader) throws IOException {
        Map<String, Object> data = new HashMap<String, Object>();
        for (int i = index; i < s.length(); ) {
            if (s.charAt(i) == '}') {
                return data;
            }
            if (s.charAt(i) != ',') {
                i = putKeyValue(s, i, data, bufferedReader);
            } else {
                i++;
            }
        }
        while ((s = bufferedReader.readLine()) != null) {
            s = s.replace(" ", "");
            s = s.replace("\t", "");
            s = s.replace("\n", "");
            for (int i = 0; i < s.length(); ) {
                if (s.charAt(i) == '}') {
                    return data;
                }
                if (s.charAt(i) != ',') {
                    i = putKeyValue(s, i, data, bufferedReader);
                } else {
                    i++;
                }
            }
        }
        return data;
    }

    void parseInlineTable(String s, int i, Map<String, Object> data, BufferedReader bufferedReader) throws IOException {
        String key;
        Map<String, Object> innerData;
        int indexEnd = s.indexOf(']', i);
        int indexPoint = s.indexOf('.', i);
        if (indexPoint != -1) {
            key = s.substring(i, indexPoint);
            innerData = new HashMap<>();
            parseInlineTable(s, indexPoint + 1, innerData, bufferedReader);
            data.put(key, innerData);
        } else {
            key = s.substring(i, indexEnd);
            innerData = new HashMap<>();
            while ((s = bufferedReader.readLine()) != null) {
                s = s.replace(" ", "");
                s = s.replace("\t", "");
                s = s.replace("\n", "");
                if (s.isEmpty()) {
                    data.put(key, innerData);
                    return;
                }
                putKeyValue(s, 0, innerData, bufferedReader);
            }
            data.put(key, innerData);
        }
    }

    void parseArrayOfTable(String s, int i, Map<String, Object> data, BufferedReader bufferedReader) throws IOException {
        String key;
        ArrayList<Object> innerArray;
        Map<String, Object> innerData = new HashMap<>();
        int indexEnd = s.indexOf(']', i);
        key = s.substring(i + 1, indexEnd);
        while ((s = bufferedReader.readLine()) != null) {
            s = s.replace(" ", "");
            s = s.replace("\t", "");
            s = s.replace("\n", "");
            if (s.isEmpty()) {
                break;
            }
            putKeyValue(s, 0, innerData, bufferedReader);
        }
        if (data.containsKey(key)) {
            innerArray = (ArrayList<Object>) data.get(key);
            innerArray.add(innerData);
        } else {
            innerArray = new ArrayList<>();
            innerArray.add(innerData);
            data.put(key, innerArray);
        }
    }

    public void write(String file, Object value, int t_count) {
        //
    }

}
