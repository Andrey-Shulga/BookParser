package com.epam.as.bookparser;

import java.util.Iterator;
import java.util.List;

/**
 * Container keeps the list of sentences.
 */
public class Paragraph extends AbstractTextComposite<Sentence> {

    private List<Sentence> components;

    @Override
    public void add(Sentence component) {
        components.add(component);
    }

    @Override
    public List<Sentence> getComponents(Class clazz) {
        return components;
    }

    @Override
    public String toSourceString() {
        return null;
    }


    @Override
    public Iterator iterator() {
        return null;
    }
}
