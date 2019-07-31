package tests.sort;

import by.talstaya.task02.component.TextComponent;
import by.talstaya.task02.component.TextComposite;
import by.talstaya.task02.parser.*;
import by.talstaya.task02.sort.DataSort;
import by.talstaya.task02.sort.SentenceSortByNumberOfWords;
import by.talstaya.task02.sort.WordSortByLength;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class WordSortByLengthTest {

    private static final String TEXT = "    Hi. Do you want to eat? I want to sleeeep. \n" +
            "    A reader will be. Yes. ";

    private String[] sorted = {
            "Hi. I want to sleeeep. Do you want to eat? ",
            "Yes. A reader will be. "
    };

    private WordParser wordParser = new WordParser();
    private LexemeParser lexemeParser = new LexemeParser(wordParser);
    private SentenceParser sentenceParser = new SentenceParser(lexemeParser);
    private ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
    private TextParser textParser = new TextParser(paragraphParser);

    @DataProvider
    public Object[] text() {
        return new Object[]{
                TEXT
        };
    }

    @Test(dataProvider = "text")
    public void testTextParser(String text) {
        TextComponent textComponent = new TextComposite(textParser.parseData(text), TextComponent.ComponentType.TEXT);
        DataSort sortSentences = new SentenceSortByNumberOfWords();
        List<TextComponent> paragraphsWithSortedSentences = sortSentences.sort(textComponent);

        for (int i = 0; i < paragraphsWithSortedSentences.size(); i++) {
            String sentence = paragraphsWithSortedSentences.get(i).toString();
            Assert.assertEquals(sentence, sorted[i]);
        }
    }
}
