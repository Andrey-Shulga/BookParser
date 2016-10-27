package com.epam.as.bookparser;

import java.util.List;

/**
 * Abstract class for text composite classes
 */
public abstract class AbstractTextComposite<E extends TextComponent> implements TextComposite<E> {

    private List<E> components;


    @Override
    public void add(E component) {
        components.add(component);

    }

    @Override
    public List<E> getComponents(Class clazz) {
        return components;
    }
}
