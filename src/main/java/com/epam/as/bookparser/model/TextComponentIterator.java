package com.epam.as.bookparser.model;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Iterator for text container.
 */
public class TextComponentIterator implements Iterator<TextComponent> {
    Deque<TextComponent> stack = new LinkedList<>();

    public TextComponentIterator(TextComponent root) {
        stack.add(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public TextComponent next() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }

        TextComponent node = stack.pop();
        if (node != null) {
            if (node instanceof AbstractTextComposite) {
                AbstractTextComposite ac = (AbstractTextComposite) node;
                for (Object acc : ac.components)
                    stack.add((TextComponent) acc);
            }
        }
        return node;
    }
}

