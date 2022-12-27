package lesson3.homework.converters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class JSONConverter implements Convertable {
    public Map<String, Object> read(String file) {
        Object data = new HashMap<String, Object>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(file))) {
            String s;
            if ((s = bufferedReader.readLine()) != null) {
                s = s.replace(" ", "");
                s = s.replace("\t", "");
                s = s.replace("\n", "");
                if (s.charAt(0) == '{') {
                    data = parseMap(s, 1, bufferedReader);
                } else {
                    //throw ЧЗХ
                }
            }
            if (bufferedReader.readLine() != null) {
                //throw непонятно чем кончается
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return ((Map<String, Object>) data);
    }

    public int putKeyValue(String s, int i, Map<String, Object> data, BufferedReader bufferedReader) throws IOException {
        String key = null;
        if (s.charAt(i) == '\"') {
            int indexEnd = s.indexOf('\"', i + 2);
            if (indexEnd == -1) {
                int k = 1;
            }
            key = s.substring(i + 1, indexEnd);
            i = indexEnd + 1;
            i++;
            switch (s.charAt(i)) {
                case '\"':
                    i++;
                    int endIndex = s.indexOf('"', i);
                    data.put(
                            key,
                            new String(s.substring(i, endIndex)));
                    i = endIndex + 1;
                    break;
                case '[':
                    i++;
                    data.put(key, parseArray(s, i, bufferedReader));
                    break;
                case '{':
                    i++;
                    data.put(key, parseMap(s, i, bufferedReader));
                    break;
                default:
                    int end = s.indexOf(',', i + 1);
                    if (end == -1) {
                        end = s.length();
                    }
                    String value = s.substring(i, end);
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
                    } else if (value.equals("null")) {
                        data.put(key, null);
                    }
                    i = end + 1;
                    break;
            }
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
            case '[':
                i++;
                data.add(parseArray(s, i, bufferedReader));
                break;
            case '{':
                i++;
                data.add(parseMap(s, i, bufferedReader));
                break;
            default:
                int end = s.indexOf(',', i + 1);
                if (end == -1) {
                    end = s.length() - 1;
                }
                String value = s.substring(i, end);
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
                i = end + 1;
                break;
        }
        return i;
    }

    public Map<String, Object> parseMap(String s, int index, BufferedReader bufferedReader) throws IOException {
        Map<String, Object> data = new HashMap<String, Object>();
        for (int i = index; i < s.length(); i++) {
            if (s.charAt(i) == '}') {
                return data;
            }
            i = putKeyValue(s, i, data, bufferedReader);
        }
        while ((s = bufferedReader.readLine()) != null) {
            s = s.replace(" ", "");
            s = s.replace("\t", "");
            s = s.replace("\n", "");
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '}') {
                    return data;
                }
                i = putKeyValue(s, i, data, bufferedReader);
            }
        }
        return data;
    }

    public ArrayList<Object> parseArray(String s, int index, BufferedReader bufferedReader) throws IOException {
        ArrayList<Object> data = new ArrayList<>();
        for (int i = index; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                return data;
            }
            i = putValue(s, i, data, bufferedReader);
        }
        while ((s = bufferedReader.readLine()) != null) {
            s = s.replace(" ", "");
            s = s.replace("\t", "");
            s = s.replace("\n", "");
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ']') {
                    return data;
                }
                i = putValue(s, i, data, bufferedReader);
            }
        }
        return data;
    }

    public void write(String file, Object value, int t_count) {
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(file))) {
            writeValue(value, t_count, bw, false);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void writeValue(Object value, int t_count, BufferedWriter bw, boolean array) throws IOException {
        if (value == null) {
            String s3 = "null";
            String res3 = array ? formIndent(t_count) + s3 : s3;
            bw.write(res3);
        } else {
            switch (value.getClass().toString()) {
                case "class java.util.HashMap":
                    writeMap((Map<String, Object>) value, t_count, bw);
                    break;
                case "class java.util.ArrayList":
                    writeArray((ArrayList<Object>) value, t_count, bw);
                    break;
                case "class java.lang.String":
                    String s1 = "\"" + value + "\"";
                    String res1 = array ? formIndent(t_count) + s1 : s1;
                    bw.write(res1);
                    break;
                default:
                    String s2 = value.toString();
                    String res2 = array ? formIndent(t_count) + s2 : s2;
                    bw.write(res2);
                    break;
            }
        }
    }

    private void writeMap(Map<String, Object> data, int t_count, BufferedWriter bw) throws IOException {
        bw.write(formIndent(t_count) + "{\n");
        t_count++;
        Iterator<Map.Entry<String, Object>> entries = data.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            bw.write(formIndent(t_count) + "\"" + entry.getKey() + "\" : ");
            writeValue(entry.getValue(), t_count, bw, false);
            if (entries.hasNext()) {
                bw.write(",\n");
            } else {
                bw.write("\n");
            }
        }
        t_count--;
        bw.write(formIndent(t_count) + "}");
    }

    public void writeArray(ArrayList<Object> data, int t_count, BufferedWriter bw) throws IOException {
        bw.write(formIndent(t_count) + "[\n");
        t_count++;
        var iterator = data.iterator();
        while (iterator.hasNext()) {
            writeValue(iterator.next(), t_count, bw, true);
            if (iterator.hasNext()) {
                bw.write(",\n");
            } else {
                bw.write("\n");
            }
        }
        t_count--;
        bw.write(formIndent(t_count) + "]");
    }

    private String formIndent(int t_count) {
        String s = "";
        for (int i = 0; i < t_count; i++) {
            s += "  ";
        }
        return s;
    }

}
