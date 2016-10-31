package com.epam.as.bookparser.service;

import com.epam.as.bookparser.model.Sentence;
import com.epam.as.bookparser.model.Text;
import com.epam.as.bookparser.model.TextComponent;
import com.epam.as.bookparser.model.TextComponentIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * It makes special operations on the text container.
 */
public class TextService {
    private Text text;
    Logger infoLogger = LoggerFactory.getLogger("infoLogger");

    public TextService(Text text) {
        this.text = text;
    }

    /**
     * Output all sentences of text container in ascending order of the number of their words.
     */
    public void performTextTask2() {
        TextComponent tc;
        TextComponentIterator iterator;
        Map<Sentence, Integer> sentMap = new HashMap<>();
        String WORD_REGEX = "([^\\p{Punct}\\s\t]*[^\\p{Punct}\\s\t])";
        iterator = new TextComponentIterator(this.text);
        while (iterator.hasNext()) {
            int count = 0;
            tc = (TextComponent) iterator.next();
            if (tc instanceof Sentence) {
                for (TextComponent sentPart : ((Sentence) tc).getComponents()) {
                    if (sentPart.toString().matches(WORD_REGEX)) count++;
                }
                sentMap.put((Sentence) tc, count);
            }
        }
        List list = new ArrayList(sentMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Sentence, Integer>>() {
            @Override
            public int compare(Map.Entry<Sentence, Integer> a, Map.Entry<Sentence, Integer> b) {
                return a.getValue() - b.getValue();
            }
        });
        infoLogger.info("Output all sentences of text container in ascending order of the number of their words.");
        for (Object l : list)
            System.out.println(l);
    }
}
