package com.epam.as.bookparser;

import java.util.Iterator;
import java.util.List;

/**
 * Container keeps the list of paragraphs.
 */
public class Text extends AbstractTextComposite<Paragraph> {

    private List<Paragraph> components;


    @Override
    public void add(Paragraph component) {
        components.add(component);
    }

    @Override
    public List<Paragraph> getComponents(Class clazz) {
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
