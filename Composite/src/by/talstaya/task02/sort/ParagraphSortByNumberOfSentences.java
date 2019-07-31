package by.talstaya.task02.sort;

import by.talstaya.task02.component.TextComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParagraphSortByNumberOfSentences implements DataSort {

    @Override
    public List<TextComponent> sort(TextComponent root) {
        List<TextComponent> textComponentList = new ArrayList<>();
        textComponentList = new ArrayList<TextComponent>(root.getTextComponents());
        textComponentList.sort(Comparator.comparingInt(paragraph -> paragraph.getTextComponents().size()));
        return textComponentList;
    }
}
