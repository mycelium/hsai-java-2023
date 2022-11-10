package com.hsai;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.Charset;

import com.hsai.parser.*;

public class Main {
    public static void main(String[] args) {
        try {
            String fileString = Files.readString(Path.of(args[0]));
            int numberingSystem = Integer.parseInt(args[1]);
            Charset encoding = Parser.getEncoding(args[2]);
            System.out.println(Parser.parse(fileString, numberingSystem, encoding));
        } 
        catch (Exception exc) {
            System.err.println(exc);
        }
    }
}
