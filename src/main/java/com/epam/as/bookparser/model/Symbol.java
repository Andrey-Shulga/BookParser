package com.epam.as.bookparser.model;

/**
 * Keeps 1 symbol.
 */
public class Symbol implements TextLeaf<TextComponent> {

    private char ch;
    static final Symbol cache[] = new Symbol[65535];

    static {
        for (int i = 0; i < cache.length; i++)
            cache[i] = new Symbol((char) i);
    }

    public Symbol(char ch) {
        this.ch = ch;
    }

    public static Symbol of(char ch) {
        if (ch <= 65535) return cache[(int) ch];
        else return new Symbol(ch);
    }

    @Override
    public void toSourceString(StringBuilder builder) {
        builder.append(ch);
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }
}
