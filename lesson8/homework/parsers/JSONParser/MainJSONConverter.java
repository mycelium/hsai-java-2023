package lesson8.homework.parsers.JSONParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class MainJSONConverter {
    private static String userString = null;
    private final static Path path = Path.of("lesson8/homework/example-input.json");
    private static String jsonString = null;

    public MainJSONConverter() {
        try {
            userString = Files.readString(path);
            jsonString = userString.replaceAll("\\s+", "");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) throws IOException {
        new MainJSONConverter();
        JSONObject user = new JSONObject(jsonString);
        HashMap<String, String> userHashMap = user.getHashMap();

        File file = new File("lesson8/homework/example-output.toml");
        BufferedWriter bf = null;

        bf = new BufferedWriter(new FileWriter(file));
        for (Map.Entry<String, String> entry : userHashMap.entrySet()) {
            if (!user.checkIfJASONArray(entry.getValue())) {
                bf.write(entry.getKey() + " = " + "\"" + entry.getValue() + "\"");
                bf.newLine();
            } else if (user.checkIfJASONArray(entry.getValue())) {
                bf.newLine();
                bf.write("[" + entry.getKey() + "]");
                bf.newLine();
                JSONArray array = user.getJSONArray(entry.getKey());
                for (int i = 0; i < array.getSize(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    HashMap<String, String> arrayElemHashMap = obj.getHashMap();
                    for (Map.Entry<String, String> arrEntry : arrayElemHashMap.entrySet()) {
                        if (!user.checkIfJASONArray(arrEntry.getValue())) {
                            bf.write(arrEntry.getKey() + " = " + "\"" + arrEntry.getValue() + "\"");
                            bf.newLine();
                        }
                    }
                }
                bf.newLine();
            }
            bf.flush();
        }
    }
}
