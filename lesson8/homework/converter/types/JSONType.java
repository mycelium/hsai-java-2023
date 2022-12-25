package lesson8.homework.converter.types;

import lesson8.homework.converter.structures.*;
import lesson8.homework.exceptions.UnfinishedToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONType implements BaseType{
    ArrayList<Value> contents = new ArrayList<Value>();

    char readChar(BufferedReader input) {
        try {
            char c = (char)(input.read());
            return c;
        } catch (IOException e) {
            return '\u001a';
        }
    }
    public Word readWord(BufferedReader input) throws UnfinishedToken {
        char symbol = readChar(input);
        StringBuilder out = new StringBuilder("");
        while (symbol != '\"' && symbol != '\u001a' && symbol != '\uFFFF' && symbol != '\n') {
            out.append(symbol);
            symbol = readChar(input);
        }
        if (symbol == '\uFFFF' || symbol == '\n') {
            throw new UnfinishedToken("Word", out.toString());
        } else {
            return new Word(out.toString());
        }
    }
    public Numbr readNumber(BufferedReader input) {
        return null;
    }
    public Array readArray(BufferedReader input) {
        return null;
    }
    public Table readTable(BufferedReader input) {
        ArrayList<Pair> table = new ArrayList<>();
        char symbol = readChar(input);
        while(symbol != '\u001a' && symbol != '\uFFFF' && symbol != '}') {
            Word key = new Word("");
            Value value = new Word("");
            if(symbol == '\"') {
                try {
                    key = readWord(input);
                } catch(UnfinishedToken e) {
                    System.out.println("Error reading Table");
                    return new Table(table);
                }
                while (symbol != ':' && symbol != '\u001a' && symbol != '\uFFFF') {
                    symbol = readChar(input);
                }
                if (symbol != ':') {
                    System.out.println("Error reading Table");
                    return new Table(table);
                }
                while (symbol != '\"' && symbol != '{' && symbol != '\u001a' && symbol != '\uFFFF') {
                    symbol = readChar(input);
                }
                if (symbol == '\"') {
                    try {
                        value = readWord(input);
                    } catch(UnfinishedToken e) {
                        System.out.println("Error reading Table");
                        return new Table(table);
                    }
                } else
                if (symbol == '{') {
                    value = readTable(input);
                } else {
                    System.out.println("Error reading Table");
                    return new Table(table);
                }
                while (symbol != ',' && symbol != '\u001a' && symbol != '\uFFFF') {
                    symbol = readChar(input);
                }
                table.add(new Pair(key, value));
            }
            symbol = readChar(input);
        }
        return new Table(table);
    }
    public BufferedWriter write(BufferedWriter output, Word word) {
        try {
            output.write((int)('\"'));
            output.write(word.toStr());
            output.write((int)('\"'));
        } catch (IOException e) {
            System.out.println("Output Error!");
        }
        return output;
    }
    public BufferedWriter write(BufferedWriter output, Numbr number) {
        return output;
    }
    public BufferedWriter write(BufferedWriter output, Array array) {
        return output;
    }
    public BufferedWriter write(BufferedWriter output, Table table) {
        Iterator<Pair> iter = table.getTbl().iterator();
        try {
            output.write("{\n");
        } catch (IOException e) {
            System.out.println("Output Error!");
        }
        while(iter.hasNext()) {
            Pair P = iter.next();
            try {
                write(output, P.s());
                output.write(" : ");
                if (P.v() instanceof Word) {
                    write(output, (Word)P.v());
                } else
                if (P.v() instanceof Numbr) {
                    output = write(output, (Numbr)P.v());
                } else
                if (P.v() instanceof Array) {
                    output = write(output, (Array)P.v());
                } else
                if (P.v() instanceof Table) {
                    output = write(output, (Table)P.v());
                }
                output.write("\n");
            } catch (IOException e) {
                System.out.println("Output Error!");
            }
        }
        try {
            output.write("}\n");
        } catch (IOException e) {
            System.out.println("Output Error!");
        }
        return output;
    }

    public ArrayList<Value> read(BufferedReader input) {
        contents = new ArrayList<Value>();
        char symbol = readChar(input);
        while(symbol != '\u001a' && symbol != '\uFFFF') {
            try {
                switch(symbol) {
                    case('\"'):  {
                        contents.add(readWord(input));
                        break;
                    }
                    case('{'): {
                        contents.add(readTable(input));
                        break;
                    }
                    default:
                }
            } catch (UnfinishedToken e) {
                System.out.println(e.getMessage());
            }
            symbol = readChar(input);
        }
        return contents;
    }
    public void write(BufferedWriter output, ArrayList<Value> value) {
        Iterator<Value> iter = value.iterator();
        while(iter.hasNext()) {
            Value obj = iter.next();
            if (obj instanceof Word) {
                write(output, (Word)obj);
            }
            if (obj instanceof Numbr) {
                output = write(output, (Numbr)obj);
            }
            if (obj instanceof Array) {
                output = write(output, (Array)obj);
            }
            if (obj instanceof Table) {
                output = write(output, (Table)obj);
            }
        }
    }
}
