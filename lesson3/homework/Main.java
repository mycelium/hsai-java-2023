package lesson3.homework;

import lesson3.homework.exceptions.InvalidFormatExeption;
import lesson3.homework.converters.BasicConverter;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String,Object> data = BasicConverter.read("C:\\Users\\fedot\\IdeaProjects\\hsai-java-2023-4\\lesson3\\homework\\data.json", "json");
            BasicConverter.write("C:\\Users\\fedot\\IdeaProjects\\hsai-java-2023-4\\lesson3\\homework\\output.toml", "toml", data);
        } catch (InvalidFormatExeption e) {
            System.err.println(e);
        }
    }
}
