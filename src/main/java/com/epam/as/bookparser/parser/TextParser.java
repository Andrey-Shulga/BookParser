package com.epam.as.bookparser.parser;

import com.epam.as.bookparser.model.Symbol;
import com.epam.as.bookparser.model.Text;
import com.epam.as.bookparser.model.TextComponent;
import com.epam.as.bookparser.model.TextComposite;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
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
    public Text parse(InputStream in) throws IllegalAccessException, InstantiationException, IOException {
        Scanner scanner = new Scanner(in).useDelimiter("\\Z");
        String source = scanner.next();
        return parseTo(source, Text.class);
    }


    @Override
    public <T extends TextComposite> T parseTo(String source, Class<T> compositeClass) {
        TextComposite composite = null;
        try {
            composite = compositeClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            //TODO ParserExeption
        }
        Class<? extends TextComponent> componentClass = componentClasses.get(compositeClass);
        if (componentClass == Symbol.class) {
            for (int i = 0; i < source.length(); i++) {
                char c = source.charAt(i);
                Symbol symbol = new Symbol(c);
                composite.add(symbol);
            }
        } else {
            String regex = regexes.get(compositeClass);
            Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(source);
            while (matcher.find()) {
                String part = matcher.group();
                if (part.length() == 1) {
                    Symbol symbol = new Symbol(part.charAt(0));
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
