package com.epam.as.bookparser;

import com.epam.as.bookparser.model.Text;
import com.epam.as.bookparser.parser.RegExTextParser;

import java.io.*;

/**
 * This program parse a text on its parts.
 * Return texts parts in one text container
 *
 * @author Andrey Shulga
 * @version 1.0 2016-10-29
 */
public class BookReaderTest {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        String bookFileName = "book.txt";
        String bookCopyFileName = "bookCopy.txt";

        //Create and configure parser
        RegExTextParser parser = new RegExTextParser();
        parser.configure();

        //Parse text from file on its parts and return back in one text container
        InputStream in = new FileInputStream(bookFileName);
        Text text = parser.parse(in);

        //Write text container to file
        try (BufferedWriter out = new BufferedWriter(new FileWriter(bookCopyFileName))) {
            out.write(text.toSourceString());
        }

    }
}
