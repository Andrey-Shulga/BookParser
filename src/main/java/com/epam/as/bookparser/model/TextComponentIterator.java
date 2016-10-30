package com.epam.as.bookparser.model;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

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

        TextComponent node = stack.pop();
        if (node != null) {
            if (node instanceof AbstractTextComposite) {
                AbstractTextComposite tc = (AbstractTextComposite) node;
                for (Object o : tc.components)
                    stack.add((TextComponent) o);
            }
        }
        return node;
    }
}

