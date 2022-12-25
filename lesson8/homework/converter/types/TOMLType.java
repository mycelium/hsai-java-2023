package lesson8.homework.converter.types;

import lesson8.homework.converter.structures.*;
import lesson8.homework.exceptions.UnfinishedToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TOMLType implements BaseType{
    ArrayList<Value> contents = new ArrayList<Value>();
    char readChar(BufferedReader input) {
        try {
            return (char)(input.read());
        } catch (IOException e) {
            return '\u001a';
        }
    }
    public Word readWord(BufferedReader input) throws UnfinishedToken  {
        return null;
    }
    public Numbr readNumber(BufferedReader input) {
        return null;
    }
    public Array readArray(BufferedReader input) {
        return null;
    }
    public Table readTable(BufferedReader input) {
        return null;
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
        ArrayList<Pair> tabl = new ArrayList<>();
        Iterator<Pair> iter = table.getTbl().iterator();
        try {
            output.write("");
        } catch (IOException e) {
            System.out.println("Output Error!");
        }
        while(iter.hasNext()) {
            Pair P = iter.next();
            try {
                boolean tab = false;
                if (P.v() instanceof Word) {
                    output.write(P.s().toStr());
                    output.write(" = ");
                    write(output, (Word)P.v());
                } else
                if (P.v() instanceof Numbr) {
                    output.write(P.s().toStr());
                    output.write(" = ");
                    write(output, (Numbr)P.v());
                } else
                if (P.v() instanceof Array) {
                    output.write(P.s().toStr());
                    output.write(" = ");
                    write(output, (Array)P.v());
                } else
                if (P.v() instanceof Table) {
                    tabl.add(P);
                    output.write("\n[");
                    output.write(P.s().toStr());
                    output.write("]\n");
                    output = write(output, (Table)P.v());
                }
                output.write("\n");
            } catch (IOException e) {
                System.out.println("Output Error!");
            }
        }
        return output;
    }

    public ArrayList<Value> read(BufferedReader input) {
        contents = new ArrayList<Value>();

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
                ArrayList<Pair> notabl = new ArrayList<>();
                ArrayList<Pair> tabl = new ArrayList<>();
                for (Pair p : ((Table)obj).getTbl()) {
                    if (p.v().getClass() == Table.class) {
                        tabl.add(p);
                    } else {
                        notabl.add(p);
                    }
                }
                output = write(output, new Table(notabl));
                output = write(output, new Table(tabl));
            }
        }
    }
}
