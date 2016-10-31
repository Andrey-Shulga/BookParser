import com.epam.as.bookparser.exception.ParserException;
import com.epam.as.bookparser.model.TextComponent;
import com.epam.as.bookparser.parser.RegExTextParser;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 */
public class TextParserTest {
    private static final String TEST_STRING = "This is my first sentence.\n This is second! This a three.\n Why so seriously, Yes.";

    private static final String EXPECTED = "This is my first sentence.\n This is second! This a three.\n Why so seriously, Yes.";


    @Test
    public void testParse() throws ParserException {
        RegExTextParser parser = new RegExTextParser();
        parser.configure();
        TextComponent component = parser.parse(TEST_STRING);
        String actual = component.toString();
        assertEquals(EXPECTED, actual);
    }

}


