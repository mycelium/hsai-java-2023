package lesson8.homework.JSON;

import lesson8.homework.Converter.InterfaceConverter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class JSWriter implements InterfaceConverter {

    public void write(String file, Object o, int i) {
        try {
            FileWriter writer = new FileWriter(file);
            writeO(o, i, writer, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeO(Object o, int i, FileWriter fileWriter, boolean array) throws IOException {
        if (o == null) {
            String s0 = "null";
            String res0 = array ? formIndent(i) + s0 : s0;
            fileWriter.write(res0);
        } else {
            switch (o.getClass().toString()) {
                case "class java.util.HashMap" -> writeMap((Map<String, Object>) o, i, fileWriter);
                case "class java.util.ArrayList" -> writeArray((ArrayList<Object>) o, i, fileWriter);
                case "class java.lang.String" -> {
                    String s1 = "\"" + o + "\"";
                    String res1 = array ? formIndent(i) + s1 : s1;
                    fileWriter.write(res1);
                }
                default -> {
                    String s2 = o.toString();
                    String res2 = array ? formIndent(i) + s2 : s2;
                    fileWriter.write(res2);
                }
            }
        }
    }

    private void writeMap(Map<String, Object> map, int i, FileWriter fileWriter) throws IOException {
        fileWriter.write(formIndent(i) + "{\n");
        i++;
        Iterator<Map.Entry<String, Object>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Object> entry = entryIterator.next();
            fileWriter.write(formIndent(i) + "\"" + entry.getKey() + "\" : ");
            writeO(entry.getValue(), i, fileWriter, false);
            if (entryIterator.hasNext()) fileWriter.write(",\n");
            else fileWriter.write("\n");
        }
        i--;
        fileWriter.write(formIndent(i) + "}");
    }

    public void writeArray(ArrayList<Object> arrayList, int i, FileWriter fileWriter) throws IOException {
        fileWriter.write(formIndent(i) + "[\n");
        i++;
        var objectIterator = arrayList.iterator();
        while (objectIterator.hasNext()) {
            writeO(objectIterator.next(), i, fileWriter, true);
            if (objectIterator.hasNext()) fileWriter.write(",\n");
            else fileWriter.write("\n");
        }
        i--;
        fileWriter.write(formIndent(i) + "]");
    }

    private String formIndent(int i) {
        return "  ".repeat(Math.max(0, i));
    }

    @Override
    public Map<String, Object> read(String file) {
        return null;
    }
}

