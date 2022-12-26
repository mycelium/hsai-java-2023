package lesson8.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TOMLParser {
    public Map<String, Object> read(String file) {
        Map<String, Object> data = new HashMap<>();
        try (BufferedReader bufferedreader = Files.newBufferedReader(Path.of(file))) {
            String s;
            while ((s = bufferedreader.readLine()) != null) {
                s = s.replace(" ", "");
                if (!s.isEmpty())
                    putInMap(s, 0, data, bufferedreader);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return data;
    }

    public Map<String, Object> parseMap(String s, int index, BufferedReader bufferedreader) throws IOException {
        Map<String, Object> data = new HashMap<String, Object>();
        for (int i = index; i < s.length(); ) {
            if (s.charAt(i) == '}') {
                return data;
            }
            i = putInMap(s, i, data, bufferedreader);
        }
        while ((s = bufferedreader.readLine()) != null) {
            s = s.replace(" ", "");
            for (int i = 0; i < s.length(); ) {
                if (s.charAt(i) == '}') {
                    return data;
                }
                i = putInMap(s, i, data, bufferedreader);
            }
        }
        return data;
    }

    public ArrayList<Object> parseArray(String s, int index, BufferedReader bufferedreader) throws IOException {
        ArrayList<Object> data = new ArrayList<>();
        for (int i = index; i < s.length(); ) {
            if (s.charAt(i) == ']') {
                return data;
            }
            i = putInArray(s, i, data, bufferedreader);
        }

        while ((s = bufferedreader.readLine()) != null) {
            s = s.replace(" ", "");
            for (int i = 0; i < s.length(); ) {
                if (s.charAt(i) == ']') {
                    return data;
                }
                if (s.charAt(i) != ',') {
                    i = putInArray(s, i, data, bufferedreader);
                } else {
                    i++;
                }
            }
        }
        return data;
    }

    void parseInlineTable(String s, int i, Map<String, Object> data, BufferedReader bufferedreader) throws IOException {
        String key;
        Map<String, Object> data2  = new HashMap<>();
        int end = s.indexOf(']', i);
        int dot = s.indexOf('.', i);
        if (dot != -1) {
            key = s.substring(i, dot);
            parseInlineTable(s, dot + 1, data2, bufferedreader);
            data.put(key, data2);
        } else {
            key = s.substring(i, end);
            while ((s = bufferedreader.readLine()) != null) {
                s = s.replace(" ", "");
                if (s.isEmpty()) {
                    data.put(key, data2);
                    return;
                }
                putInMap(s, 0, data2, bufferedreader);
            }
            data.put(key, data2);
        }
    }

    void parseArrayOfTable(String s, int i, Map<String, Object> data, BufferedReader bufferedreader) throws IOException {
        String key;
        ArrayList<Object> array;
        Map<String, Object> data2 = new HashMap<>();
        int end = s.indexOf(']', i);
        key = s.substring(i + 1, end);
        while ((s = bufferedreader.readLine()) != null) {
            s = s.replace(" ", "");
            if (s.isEmpty()) {
                break;
            }
            putInMap(s, 0, data2, bufferedreader);
        }
        if (data.containsKey(key)) {
            array = (ArrayList<Object>) data.get(key);
            array.add(data2);
        } else {
            array = new ArrayList<>();
            array.add(data2);
            data.put(key, array);
        }
    }

    public int putInMap(String s, int i, Map<String, Object> data, BufferedReader bufferedreader) throws IOException {
        String key = null;
        int end;
        switch (s.charAt(i)) {
            case '\"':
                end = s.indexOf('"', i + 1);
                key = s.substring(i + 1, end);
                end++;
                break;
             case '[':
                if (s.charAt(i + 1) == '[') {
                    parseArrayOfTable(s, i + 1, data, bufferedreader);
                } else {
                    parseInlineTable(s, i + 1, data, bufferedreader);
                }
                 return s.length();

            default:
                end = s.indexOf('=', i + 1);
                int dot = s.substring(i + 1, end).indexOf(".") + i + 1;
                if (dot != -1 && dot != i) {
                    key = s.substring(i, dot);
                    if (data.containsKey(key)) {
                        i = putInMap(s, dot + 1, (Map<String, Object>) data.get(key), bufferedreader);
                    } else {
                        Map<String, Object> data2 = new HashMap<>();
                        i = putInMap(s, dot + 1, data2, bufferedreader);
                        data.put(key, data2);
                    }
                    return i;
                }
                key = s.substring(i, end);
                break;
        }
        int end2;
        switch (s.charAt(end + 1)) {
            case '\"':
                end2 = s.indexOf('"', end + 2);
                data.put(key, new String(s.substring(end + 2, end2)));
                i = end2 + 1;
                break;
            case '\'':
                end2 = s.indexOf('\'', end + 2);
                data.put(key, new String(s.substring(end + 2, end2)));
                i = end2 + 1;
                break;
            case '[':
                i++;
                data.put(key, parseArray(s, end + 2, bufferedreader));
                i = s.indexOf(']', i) + 1;
                break;
            case '{':
                i++;
                data.put(key, parseMap(s, end + 2, bufferedreader));
                i = s.indexOf('}', i) + 1;
                break;
            default:
                end = s.indexOf(',', i + 1);
                end2 = s.indexOf('}', i + 1);
                String value;
                if (end2 == -1 && end == -1) {
                    end = s.length();
                } else if ((end > end2 || end == -1) && end2 != -1) {
                    end = end2;
                }
                value = s.substring(i, end);
                i = end;
                boolean wIsIt;
                try {
                    Double.parseDouble(value);
                    wIsIt = true;
                } catch (NumberFormatException e) {
                    wIsIt = false;
                }
                if (wIsIt) {
                    try {
                        Integer.parseInt(value);
                        wIsIt = true;
                    } catch (NumberFormatException e) {
                        wIsIt = false;
                    }
                    if (wIsIt) {
                        data.put(key, Integer.parseInt(value));
                    } else {
                        data.put(key, Double.parseDouble(value));
                    }
                } else if (value.equals("null")) {
                    data.put(key, true);
                } else if (value.equals("true")) {
                    data.put(key, true);
                } else if (value.equals("false")) {
                    data.put(key, false);
                }
                break;
        }
        return i;
    }

    public int putInArray(String s, int i, ArrayList<Object> data, BufferedReader bufferedreader) throws IOException {
        int end;
        switch (s.charAt(i)) {
            case '\"':
                i++;
                end = s.indexOf('"', i);
                data.add(new String(s.substring(i, end)));
                i = end + 1;
                break;
            case '\'':
                i++;
                end = s.indexOf('\'', i);
                data.add(new String(s.substring(i, end)));
                i = end + 1;
                break;
            case '[':
                i++;
                data.add(parseArray(s, i, bufferedreader));
                i = s.indexOf(']', i) + 1;
                break;
            case '{':
                i++;
                data.add(parseMap(s, i, bufferedreader));
                i = s.indexOf('}', i) + 1;
                break;
            default:
                end = s.indexOf(',', i + 1);
                int end2 = s.indexOf(']', i + 1);
                String value;
                if (end2 == -1 && end == -1) {
                    end = s.length();
                } else if ((end > end2 || end == -1) && end2 != -1) {
                    end = end2;
                }
                value = s.substring(i, end);
                i = end;
                boolean wIsIt;
                try {
                    Double.parseDouble(value);
                    wIsIt = true;
                } catch (NumberFormatException e) {
                    wIsIt = false;
                }
                if (wIsIt) {
                    try {
                        Integer.parseInt(value);
                        wIsIt = true;
                    } catch (NumberFormatException e) {
                        wIsIt = false;
                    }
                    if (wIsIt) {
                        data.add(Integer.parseInt(value));
                    } else {
                        data.add(Double.parseDouble(value));
                    }
                } else if (value.equals("null")) {
                    data.add(null);
                } else if (value.equals("true")) {
                    data.add(true);
                } else if (value.equals("false")) {
                    data.add(false);
                }
                break;
        }
        return i;
    }
}