package com.epam.as.bookparser;

import java.util.Iterator;
import java.util.List;

/**
 * Keeps word
 */
public class Word extends AbstractTextComposite<Symbol> {

    private List<Symbol> components;

    @Override
    public void add(Symbol component) {
        components.add(component);
    }

    @Override
    public List<Symbol> getComponents(Class clazz) {
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
