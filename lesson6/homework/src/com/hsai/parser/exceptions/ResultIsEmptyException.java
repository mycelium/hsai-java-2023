package com.hsai.parser.exceptions;

public class ResultIsEmptyException extends Exception {
    public ResultIsEmptyException(String[] tokens, int numeralSystem) {
        super("Result array is empty! Given tokens: \"" + tokens + "\"; given numeral system: " + numeralSystem);
    }

    public ResultIsEmptyException(String str, int numeralSystem) {
        super("Result string is empty! Given string: \"" + str + "\"; given numeral system: " + numeralSystem);
    }
}
