package lesson8.homework.exceptions;

public class InvalidFormat extends Exception{
    public InvalidFormat(String stream, String format) {
        super("Invalid " + stream + " format \"" + format + "\".");
    }
}
