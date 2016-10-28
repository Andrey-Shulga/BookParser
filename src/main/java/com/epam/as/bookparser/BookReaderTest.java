package com.epam.as.bookparser;

import com.epam.as.bookparser.Parser.TextParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This program parse text on its parts.
 *
 * @author Andrey Shulga
 * @version 1.0 2016-10-27
 */
public class BookReaderTest {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        String bookFileName = "book.txt";

        TextParser parser = new TextParser();
        parser.configure();

        InputStream in = new FileInputStream(bookFileName);
        parser.parse(in);

    }
}
