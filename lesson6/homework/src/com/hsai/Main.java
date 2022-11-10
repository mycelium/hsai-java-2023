package com.hsai;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.Charset;

import com.hsai.parser.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            String fileContent = Files.readString(Path.of(args[0]));
            int numeralSystem = Integer.parseInt(args[1]);
            Charset charset = Parser.getCharset(args[2]);

            System.out.println(Parser.parse(fileContent, numeralSystem, charset));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
