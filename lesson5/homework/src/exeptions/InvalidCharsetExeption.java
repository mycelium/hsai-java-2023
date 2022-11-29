package lesson5.homework.src.exeptions;

public class InvalidCharsetExeption extends Exception{
    public InvalidCharsetExeption(String charset) {
        super("Charset \"" + charset + "\" is invalid! ");
    }
}
