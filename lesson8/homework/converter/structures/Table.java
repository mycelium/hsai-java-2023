package lesson8.homework.converter.structures;

import java.util.ArrayList;

public class Table extends Value{
    final ArrayList<Pair> table;
    public Table(ArrayList<Pair> value) {
        table = value;
    }
    public ArrayList<Pair> getTbl() {
        return table;
    }
}
