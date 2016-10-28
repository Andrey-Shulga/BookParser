package com.epam.as.bookparser.model;

/**
 * Keeps 1 symbol.
 */
public class Symbol implements TextLeaf<TextComponent> {

    private char ch;

    public Symbol(char ch) {
        this.ch = ch;
    }


    @Override
    public void toSourceString(StringBuilder builder) {
        builder.append(ch);
    }
}
