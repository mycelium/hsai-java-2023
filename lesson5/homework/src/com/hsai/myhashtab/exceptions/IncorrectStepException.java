package com.hsai.myhashtab.exceptions;

public class IncorrectStepException extends Exception {
    public IncorrectStepException(int capacity, int step) {
        super("Step (" + step + ") more than capacity (" + capacity + ").");
    }
}
