package com.hsai.parser.exceptions;

public class InvalidNumeralSystemException extends Exception {
    public InvalidNumeralSystemException(int numeralSystem) {
        super("Numeral system of " + numeralSystem + " is not in range of [2..16]!");
    }
}
