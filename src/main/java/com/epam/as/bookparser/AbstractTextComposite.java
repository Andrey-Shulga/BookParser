package com.epam.as.bookparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for text composite classes
 */
abstract class AbstractTextComposite<E extends TextComponent> implements TextComposite<E> {

    private List<E> components = new ArrayList<>();


    @Override
    public void add(E component) {
        components.add(component);

    }


    public abstract String toSourceString();
}
