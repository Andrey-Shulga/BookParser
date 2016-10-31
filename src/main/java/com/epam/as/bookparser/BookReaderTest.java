package com.epam.as.bookparser;

import com.epam.as.bookparser.exception.ParserException;
import com.epam.as.bookparser.model.Text;
import com.epam.as.bookparser.parser.RegExTextParser;
import com.epam.as.bookparser.service.TextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * This program parse a text on its parts with using regular expression.
 * Return texts parts in one text container
 *
 * @author Andrey Shulga
 * @version 1.0 2016-10-29
 */
public class BookReaderTest {
    public static void main(String[] args) throws ParserException {

        Logger errorLogger = LoggerFactory.getLogger("errorLogger");

        String bookFileName = "book.txt";
        String bookCopyFileName = "bookCopy.txt";
        Text text = null;

        //Create and configure parser
        RegExTextParser parser = new RegExTextParser();
        parser.configure();

        //Parse text from file on its parts and return back in one text container
        try (InputStream in = new FileInputStream(bookFileName)) {
            text = parser.parse(in);
        } catch (IOException e) {
            errorLogger.error("File \"{}\" not found!", bookFileName, e);
        }

        //Write text container to file
        try (BufferedWriter out = new BufferedWriter(new FileWriter(bookCopyFileName))) {
            if (text != null) out.write(text.toSourceString());
        } catch (IOException e) {
            errorLogger.error("Can''t write to file: \"{}\" ", bookCopyFileName, e);
        }

        TextService textService = new TextService(text);

        //Output all sentences of this text in ascending order of the number of their words.
        textService.performTextTask2();

    }
}
