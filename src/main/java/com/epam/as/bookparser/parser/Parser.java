package com.epam.as.bookparser.parser;

import com.epam.as.bookparser.exception.ParserException;
import com.epam.as.bookparser.model.Text;
import com.epam.as.bookparser.model.TextComposite;

import java.io.IOException;
import java.io.InputStream;

/**
 * An universal interface for parser.
 */
interface Parser {

    Text parse(InputStream in) throws IOException, ParserException;

    <T extends TextComposite> T parseTo(String source, Class<T> compositeClass) throws ParserException;
}
