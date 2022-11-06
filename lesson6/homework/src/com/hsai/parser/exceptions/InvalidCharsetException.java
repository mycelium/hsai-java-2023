package com.hsai.parser.exceptions;

public class InvalidCharsetException extends Exception {
    public InvalidCharsetException(String charset) {
        super("Charset \"" + charset + "\" is invalid! Supported charsets: ASCII, UTF-8, UTF-16.");
    }
}
