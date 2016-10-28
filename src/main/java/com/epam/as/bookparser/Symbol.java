package com.epam.as.bookparser;

/**
 * Keeps 1 symbol.
 */
public class Symbol implements TextLeaf<TextComponent> {

    private char ch;

    public Symbol(char ch) {
        this.ch = ch;
    }


    @Override
    public String toSourceString() {
        return String.valueOf(ch);
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }
}
