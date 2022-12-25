package lesson8.homework.converter.structures;

public class Word extends Value{
    final String word;
    public Word(String value) {
        word = value;
    }
    public String toStr() {
        return word;
    }
}
