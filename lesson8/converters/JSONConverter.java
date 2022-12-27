package lesson8.converters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class JSONConverter {
    public void write(String file, Object value) {
        try (BufferedWriter bufferwriter = Files.newBufferedWriter(Path.of(file))) {
            writeValue(value, bufferwriter);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void writeValue(Object value, BufferedWriter bufferwriter) throws IOException {
        if (value == null) {
            bufferwriter.write("null");
        } else {
            switch (value.getClass().toString()) {
                case "class java.util.HashMap":
                    writeMap((Map<String, Object>) value, bufferwriter);
                    break;
                case "class java.util.ArrayList":
                    writeArray((ArrayList<Object>) value, bufferwriter);
                    break;
                case "class java.lang.String":
                    bufferwriter.write("\"" + value + "\"");
                    break;
                default:
                    bufferwriter.write(value.toString());
                    break;
            }
        }
    }

    private void writeMap(Map<String, Object> data, BufferedWriter bufferwriter) throws IOException {
        bufferwriter.write("{\n");
        Iterator<Map.Entry<String, Object>> entries = data.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            bufferwriter.write("\"" + entry.getKey() + "\" : ");
            writeValue(entry.getValue(), bufferwriter);
            if (entries.hasNext()) {
                bufferwriter.write(",\n");
            } else {
                bufferwriter.write("\n");
            }
        }
        bufferwriter.write("}");
    }

    public void writeArray(ArrayList<Object> data, BufferedWriter bufferwriter) throws IOException {
        bufferwriter.write("[\n");
        var iterator = data.iterator();
        while (iterator.hasNext()) {
            writeValue(iterator.next(), bufferwriter);
            if (iterator.hasNext()) {
                bufferwriter.write(",\n");
            } else {
                bufferwriter.write("\n");
            }
        }
        bufferwriter.write("]");
    }
}