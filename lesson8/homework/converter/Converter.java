package lesson8.homework.converter;

import lesson8.homework.exceptions.*;
import lesson8.homework.converter.types.*;
import lesson8.homework.converter.structures.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Converter {
    static Map<String, BaseType> types;
    {
        types = new HashMap<>();
        types.put("JSON", new JSONType());
        types.put("TOML", new TOMLType());
    }
    BaseType inType;
    BaseType outType;
    BufferedReader input;
    BufferedWriter output;
    public Converter(String inputType, String inputFile, String outputType, String outputFile) throws InvalidFormat {
        if (types.containsKey(inputType)) {
            inType = types.get(inputType);
        }
        else {
            throw new InvalidFormat("input", inputType);
        }
        try {
            input = Files.newBufferedReader(Path.of(inputFile));
        }
        catch (IOException e) {
            System.err.println(e);
        }
        if (types.containsKey(outputType)) {
            outType = types.get(outputType);
        }
        else {
            throw new InvalidFormat("output", outputType);
        }
        try {
            output = Files.newBufferedWriter(Path.of(outputFile));
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
    public void convert() {
        outType.write(output, inType.read(input));
        try {
            output.close();
        } catch (IOException e) {
            System.out.println("Output Error!");
        }
    }
}
