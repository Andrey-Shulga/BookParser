package com.epam.as.bookparser.Parser;

import com.epam.as.bookparser.Text;
import com.epam.as.bookparser.TextComposite;

import java.io.IOException;
import java.io.InputStream;

/**
 * An universal interface for parser.
 */
interface Parser {

    Text parse(InputStream in) throws IllegalAccessException, InstantiationException, IOException;

    <T extends TextComposite> T parseTo(String source, Class<T> compositeClass) throws IllegalAccessException, InstantiationException;
}
