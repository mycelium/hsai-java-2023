package lesson8.homework.Converter;

import java.util.Map;

public interface InterfaceConverter {
    public Map<String, Object> read(String file);

    void write(String file, Object value, int t_count);
}
