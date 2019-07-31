package tests.sort;

import by.talstaya.task02.component.TextComponent;
import by.talstaya.task02.component.TextComposite;
import by.talstaya.task02.parser.*;
import by.talstaya.task02.sort.DataSort;
import by.talstaya.task02.sort.ParagraphSortByNumberOfSentences;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ParagraphSortByNumberOfSentencesTest {

    private static final String TEXT = "    Hi. Do you want to eat? I want to sleeeep. Hahaha. \n" +
            "    True story. Or maybe not true. Who knows. \n" +
            "    A a reader will be, yes. \n" +
            "    Bye. ";

    private String[] sorted = {
            "A a reader will be, yes. ",
            "Bye. ",
            "True story. Or maybe not true. Who knows. ",
            "Hi. Do you want to eat? I want to sleeeep. Hahaha. "
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
        DataSort paragraphSort = new ParagraphSortByNumberOfSentences();
        List<TextComponent> sortedList = paragraphSort.sort(textComponent);

        for (int i = 0; i < sortedList.size(); i++) {
            String paragraph = sortedList.get(i).toString();
            Assert.assertEquals(paragraph, sorted[i]);
        }
    }
}
