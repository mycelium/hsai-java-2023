package lesson8.homework.converter.types;

import lesson8.homework.converter.structures.*;
import lesson8.homework.exceptions.UnfinishedToken;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;

public interface BaseType {
    ArrayList<Value> contents = new ArrayList<Value>();
    Word readWord(BufferedReader input) throws UnfinishedToken;
    Numbr readNumber(BufferedReader input);
    Array readArray(BufferedReader input);
    Table readTable(BufferedReader input);
    BufferedWriter write(BufferedWriter output, Word word);
    BufferedWriter write(BufferedWriter output, Numbr number);
    BufferedWriter write(BufferedWriter output, Array array);
    BufferedWriter write(BufferedWriter output, Table table);

    ArrayList<Value> read(BufferedReader input);
    void write(BufferedWriter output, ArrayList<Value> value);
}
