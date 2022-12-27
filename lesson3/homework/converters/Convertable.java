package lesson3.homework.converters;

import java.io.BufferedReader;
import java.util.Map;

interface Convertable {
    public Map<String, Object> read(String file);

    public void write(String text, Object data, int t_count);
}
