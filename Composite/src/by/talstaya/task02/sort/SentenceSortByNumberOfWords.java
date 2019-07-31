package by.talstaya.task02.sort;

import by.talstaya.task02.component.TextComponent;

import java.util.Comparator;
import java.util.List;

public class SentenceSortByNumberOfWords implements DataSort {

    private static final Comparator<TextComponent> COMPARATOR =
            Comparator.comparingInt(sentenceComposite -> extractWordsFromSentence(sentenceComposite).size());

    private static List<TextComponent> extractWordsFromSentence(final TextComponent sentenceComposite) {
        ComponentsExtractor componentsExtractor = new ComponentsExtractor();
        List<TextComponent> extractedWordsFromSentence = componentsExtractor.extractComponents(sentenceComposite, TextComponent.ComponentType.WORD);

        return extractedWordsFromSentence;
    }

    @Override
    public List<TextComponent> sort(TextComponent root) {
        List<TextComponent> extractedParagraphs = root.getTextComponents();
        extractedParagraphs.forEach(paragraph -> paragraph.getTextComponents().sort(COMPARATOR));

        return extractedParagraphs;
    }
}
