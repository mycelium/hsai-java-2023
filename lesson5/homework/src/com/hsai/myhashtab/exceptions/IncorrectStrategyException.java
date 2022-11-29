package com.hsai.myhashtab.exceptions;

public class IncorrectStrategyException extends Exception {
    public IncorrectStrategyException() {
        super("You entered an invalid search strategy number.");
    }
}
