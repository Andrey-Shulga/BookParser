package com.epam.as.bookparser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for text composite classes
 */
abstract class AbstractTextComposite<E extends TextComponent> implements TextComposite<E> {

    List<E> components = new ArrayList<>();

    @Override
    public void add(E component) {
        components.add(component);
    }


    public void toSourceString(StringBuilder builder) {
        for (E component : components)
            component.toSourceString(builder);
    }

    public String toSourceString() {
        StringBuilder builder = new StringBuilder();
        toSourceString(builder);
        return builder.toString();
    }

    @Override
    public String toString() {
        return toSourceString();
    }

    public List<E> getComponents() {
        return components;
    }
}
