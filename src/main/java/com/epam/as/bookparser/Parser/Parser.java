package com.epam.as.bookparser.Parser;

import com.epam.as.bookparser.Text;
import com.epam.as.bookparser.TextComposite;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

/**
 * An universal interface for parser.
 */
public interface Parser {
    Text parse(String source) throws InstantiationException, IllegalAccessException;

    Text parse(File file);

    Text parse(InputStream in) throws IllegalAccessException, InstantiationException;

    Text parse(Reader reader);

    <T extends TextComposite> T parseTo(String source, Class<T> compositeClass) throws IllegalAccessException, InstantiationException;
}
