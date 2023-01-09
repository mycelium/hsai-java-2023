package lesson8.homework;

public class IOExceptions extends Exception {
    public IOExceptions(String string) {
        super("String \"" + string + "\" is invalid! Try again!");
    }
}
