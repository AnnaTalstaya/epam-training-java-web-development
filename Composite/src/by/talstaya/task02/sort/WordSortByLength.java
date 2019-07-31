package by.talstaya.task02.sort;

import by.talstaya.task02.component.TextComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WordSortByLength implements DataSort {

    private static final Comparator<TextComponent> COMPARATOR =
            Comparator.comparingInt(lexemeComposite -> extractWord(lexemeComposite).getTextComponents().size());

    private static TextComponent extractWord(final TextComponent lexemeComposite) {
        for (TextComponent component : lexemeComposite.getTextComponents()) {
            if (component.getComponentType() == TextComponent.ComponentType.WORD) {
                return component;
            }
        }
        return lexemeComposite;
    }

    @Override
    public List<TextComponent> sort(TextComponent root) {
        ComponentsExtractor componentsExtractor = new ComponentsExtractor();
        List<TextComponent> extractedSentences = componentsExtractor.extractComponents(root, TextComponent.ComponentType.SENTENCE);
        extractedSentences.forEach(sentence -> {
            sentence.getTextComponents().sort(COMPARATOR);
        });
        return extractedSentences;
    }
}







