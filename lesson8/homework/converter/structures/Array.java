package lesson8.homework.converter.structures;

import java.util.ArrayList;

public class Array extends Value {
    final ArrayList<Value> array;
    public Array(ArrayList<Value> value) {
        array = value;
    }
}
