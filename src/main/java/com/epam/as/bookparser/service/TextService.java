package com.epam.as.bookparser.service;

import com.epam.as.bookparser.model.Text;
import com.epam.as.bookparser.model.TextComponentIterator;

/**
 * It makes operations on text container.
 */
public class TextService {
    private Text text;
    TextComponentIterator iterator = new TextComponentIterator(text);

    public TextService(Text text) {
        this.text = text;
    }


}
