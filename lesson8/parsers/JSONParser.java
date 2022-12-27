package lesson8.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class JSONParser {
    public Map<String, Object> read(String file) {
        Map<String, Object> data = new HashMap<>();
        try (BufferedReader bufferedreader = Files.newBufferedReader(Path.of(file))) {
            String s;
            if ((s = bufferedreader.readLine()) != null) {
                s = s.replace(" ", "");
                if (s.charAt(0) == '{') {
                    data = parseMap(s, 1, bufferedreader);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return (data);
    }

    public Map<String, Object> parseMap(String s, int index, BufferedReader bufferedreader) throws IOException {
        Map<String, Object> data = new HashMap<String, Object>();
        for (int i = index; i < s.length(); i++) {
            if (s.charAt(i) == '}') {
                return data;
            }
            i = putInMap(s, i, data, bufferedreader);
        }
        while ((s = bufferedreader.readLine()) != null) {
            s = s.replace(" ", "");
            for (int i = 0; i < s.length(); i++) {
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
        for (int i = index; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                return data;
            }
            i = putInArray(s, i, data, bufferedreader);
        }
        while ((s = bufferedreader.readLine()) != null) {
            s = s.replace(" ", "");
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ']') {
                    return data;
                }
                i = putInArray(s, i, data, bufferedreader);
            }
        }
        return data;
    }

    public int putInMap(String s, int i, Map<String, Object> data, BufferedReader bufferedreader) throws IOException {
        if (s.charAt(i) == '\"') {
            int end = s.indexOf('\"', i + 2);
            String key = s.substring(i + 1, end);
            i = end + 2;
            switch (s.charAt(i)) {
                case '\"':
                    end = s.indexOf('"', i + 1);
                    data.put(key, new String(s.substring(i + 1, end)));
                    i = end + 1;
                    break;
                case '{':
                    i++;
                    data.put(key, parseMap(s, i, bufferedreader));
                    break;
                case '[':
                    i++;
                    data.put(key, parseArray(s, i, bufferedreader));
                    break;
                default:
                    end = s.indexOf(',', i + 1);
                    if (end == -1) {
                        end = s.length();
                    }
                    String value = s.substring(i, end);
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
                        data.put(key, null);
                    } else if (value.equals("true")) {
                        data.put(key, true);
                    } else if (value.equals("false")) {
                        data.put(key, false);
                    }
                    i = end + 1;
                    break;
            }
        }
        return i;
    }

    public int putInArray(String s, int i, ArrayList<Object> data, BufferedReader bufferedreader) throws IOException {
        switch (s.charAt(i)) {
            case '\"':
                int end = s.indexOf('"', i + 1);
                data.add(new String(s.substring(i + 1, end)));
                i = end + 1;
                break;
            case '{':
                i++;
                data.add(parseMap(s, i, bufferedreader));
                break;
            case '[':
                i++;
                data.add(parseArray(s, i, bufferedreader));
                break;
            default:
                end = s.indexOf(',', i + 1);
                if (end == -1) {
                    end = s.length() - 1;
                }
                String value = s.substring(i, end);
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
                i = end + 1;
                break;
        }
        return i;
    }
}