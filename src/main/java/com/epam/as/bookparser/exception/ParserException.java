package com.epam.as.bookparser.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception for RegExTextParser.
 * Throws if couldn't create new composite instance by method newInstance().
 */
public class ParserException extends Exception {
    private Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    public ParserException(Throwable throwable) {
        errorLogger.error("Can''t create incompatible composite class by newInstancxe() method!", throwable);
    }
}
