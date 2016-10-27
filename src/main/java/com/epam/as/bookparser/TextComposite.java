package com.epam.as.bookparser;

import java.util.List;

/**
 * An interface for access to composite objects.
 */
public interface TextComposite<E extends TextComponent> extends TextComponent, Iterable<TextComponent> {

    void add(E component);

    List<E> getComponents(Class clazz);

}
