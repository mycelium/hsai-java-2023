package lesson8.homework.exceptions;

public class UnfinishedToken extends Exception{
    public UnfinishedToken(String type, String token) {
        super("Error reading " + type + ": " + token + ".");
    }
}
