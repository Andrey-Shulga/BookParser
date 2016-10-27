package com.epam.as.bookparser.Parser;

import com.epam.as.bookparser.Symbol;
import com.epam.as.bookparser.Text;
import com.epam.as.bookparser.TextComposite;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.as.bookparser.util.PropertyManager.propertyManager;

/**
 * Parse text on its components.
 */
public class TextParser implements Parser {

    private Map<Class, String> regexes = new HashMap<>();
    private Map<Class, Class> componentClasses = new HashMap<>();

    public void configure() throws ClassNotFoundException {

        for (Map.Entry m : propertyManager.getProperties().entrySet()) {
            Class aClass = Class.forName((String) m.getKey());
            String regex = (String) m.getValue();
            regexes.put(aClass, regex);
        }
        for (Map.Entry m : propertyManager.getProperties2().entrySet()) {
            Class aClass = Class.forName((String) m.getKey());
            Class bClass = Class.forName((String) m.getValue());
            componentClasses.put(aClass, bClass);
        }

    }

    @Override
    public Text parse(String source) throws InstantiationException, IllegalAccessException {
        return parseTo(source, Text.class);
    }

    @Override
    public Text parse(File file) {
        return null;
    }

    @Override
    public Text parse(InputStream in) throws IllegalAccessException, InstantiationException {
        Scanner scanner = new Scanner(in).useDelimiter("\\Z");
        String source = scanner.next();

        return parse(source);
    }

    @Override
    public Text parse(Reader reader) {
        return null;
    }

    @Override
    public <T extends TextComposite> T parseTo(String source, Class<T> compositeClass) throws IllegalAccessException, InstantiationException {

        String regex = regexes.get(compositeClass);
        Pattern pattern = Pattern.compile(regex, 32);
        Matcher matcher = pattern.matcher(source);
        List<String> parts = new ArrayList<>();
        while (matcher.find())
            parts.add(matcher.group());

        Class<? extends TextComposite> componentClass = componentClasses.get(compositeClass);
        TextComposite composite = componentClass.newInstance();
        for (String part : parts) {
            if (part.length() == 1) {
                Symbol symbol = new Symbol(part);
                composite.add(symbol);
            } else {
                TextComposite textComponent = parseTo(part, componentClass);
                composite.add(textComponent);
            }
        }
        return (T) composite;
    }
}
