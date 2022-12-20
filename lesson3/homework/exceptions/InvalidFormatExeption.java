package lesson3.homework.exceptions;

public class InvalidFormatExeption extends Exception{
    public InvalidFormatExeption(String format) {
        super("Format \"" + format + "\" is invalid! ");
    }
}