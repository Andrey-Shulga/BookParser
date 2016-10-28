package com.epam.as.bookparser;

/**
 * An interface for access to composite objects.
 */
public interface TextComposite<E extends TextComponent> extends TextComponent, Iterable<TextComponent> {

    void add(E component);


}
