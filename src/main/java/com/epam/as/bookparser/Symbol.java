package com.epam.as.bookparser;

/**
 * Keeps 1 symbol.
 */
public class Symbol implements TextLeaf<TextComponent> {

    char ch;

    public Symbol(String ch) {
        this.ch = ch.charAt(0);
    }

    ;

    @Override
    public String toSourceString() {
        return String.valueOf(ch);
    }
}
