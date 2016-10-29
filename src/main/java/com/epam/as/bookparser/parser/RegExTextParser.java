package com.epam.as.bookparser.parser;

import com.epam.as.bookparser.exception.ParserException;
import com.epam.as.bookparser.model.Symbol;
import com.epam.as.bookparser.model.Text;
import com.epam.as.bookparser.model.TextComponent;
import com.epam.as.bookparser.model.TextComposite;
import com.epam.as.bookparser.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse text on its components.
 */
public class RegExTextParser implements Parser {

    private Logger logger = LoggerFactory.getLogger("RegExTextParser");
    private static final String PROPERTY_PARSER_FILE_NAME = "parser.properties";
    private static final String PROPERTY_TEXT_PARTS_FILE_NAME = "textparts.properties";
    private Map<Class, String> regExes = new HashMap<>();
    private Map<Class, Class> componentClasses = new HashMap<>();


    public void configure() {

        PropertyManager pManagerRegEx = new PropertyManager(PROPERTY_PARSER_FILE_NAME);
        PropertyManager pManagerTextParts = new PropertyManager(PROPERTY_TEXT_PARTS_FILE_NAME);

        for (Map.Entry m : pManagerRegEx.getProperties().entrySet()) {
            Class aClass = null;
            try {
                aClass = Class.forName((String) m.getKey());
            } catch (ClassNotFoundException e) {
                logger.error(MessageFormat.format("Class \"{0}\" from property file \"{1}\" not found!", m.getKey(), PROPERTY_PARSER_FILE_NAME), e);
            }
            String regEx = (String) m.getValue();
            regExes.put(aClass, regEx);
        }

        for (Map.Entry m : pManagerTextParts.getProperties().entrySet()) {
            Class aClass = null;
            Class bClass = null;
            try {
                aClass = Class.forName((String) m.getKey());
                bClass = Class.forName((String) m.getValue());
            } catch (ClassNotFoundException e) {
                logger.error(MessageFormat.format("Class \"{0}\" or \"{1}\" from property file \"{2}\" not found!", m.getKey(), m.getValue(), PROPERTY_TEXT_PARTS_FILE_NAME), e);
            }
            componentClasses.put(aClass, bClass);
        }

    }

    @Override
    public Text parse(InputStream in) throws ParserException {
        String source = "";
        try (Scanner scanner = new Scanner(in).useDelimiter("\\Z")) {
            if (scanner.hasNext()) source = scanner.next();
        }
        return parseTo(source, Text.class);
    }


    @Override
    public <T extends TextComposite> T parseTo(String source, Class<T> compositeClass) throws ParserException {

        TextComposite composite;
        try {
            composite = compositeClass.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            throw new ParserException("Program was interrupted, because composite object couldn't be create by newInstance() method:", e);
        }
        Class<? extends TextComponent> componentClass = componentClasses.get(compositeClass);
        if (componentClass == Symbol.class) {
            for (int i = 0; i < source.length(); i++) {
                char c = source.charAt(i);
                Symbol symbol = Symbol.of(c);
                composite.add(symbol);
            }
        } else {
            String regex = regExes.get(compositeClass);
            Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(source);
            while (matcher.find()) {
                String part = matcher.group();
                if (part.length() == 1) {
                    char c = part.charAt(0);
                    Symbol symbol = Symbol.of(c);
                    composite.add(symbol);
                } else {
                    TextComponent textComponent = parseTo(part, (Class<T>) componentClass);
                    composite.add(textComponent);

                }
            }
        }

        return (T) composite;

    }
}
