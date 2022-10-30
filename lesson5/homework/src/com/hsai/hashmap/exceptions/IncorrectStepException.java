package com.hsai.hashmap.exceptions;

public class IncorrectStepException extends Exception {
    public IncorrectStepException(int step, int capacity) {
        super("Step value is incorrect! Value  " + step +
                " is zero or exceeds capacity of " + capacity);
    }
}
