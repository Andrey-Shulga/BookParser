package com.epam.as.bookparser.model;

/**
 * A general interface for containers and its elements
 */
public interface TextComponent {
    void toSourceString(StringBuilder builder);
}
