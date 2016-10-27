package com.epam.as.bookparser;

import java.util.Iterator;
import java.util.List;

/**
 * Container keeps the list of sentence parts (words, whitespaces, punctuations mark).
 */
public class Sentence extends AbstractTextComposite<Word> {

    private List<Word> components;

    @Override
    public void add(Word component) {
        components.add(component);
    }

    @Override
    public List<Word> getComponents(Class clazz) {
        return components;
    }

    @Override
    public String toSourceString() {
        return null;
    }


    @Override
    public Iterator<TextComponent> iterator() {
        return null;
    }
}
