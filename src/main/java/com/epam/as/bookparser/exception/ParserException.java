package com.epam.as.bookparser.exception;

/**
 * Exception for RegExTextParser.
 * Throws if couldn't create new composite instance by method newInstance().
 */
public class ParserException extends Exception {
    public ParserException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
