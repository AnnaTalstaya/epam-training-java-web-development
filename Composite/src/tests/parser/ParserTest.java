package tests.parser;

import by.talstaya.task02.component.TextComponent;
import by.talstaya.task02.component.TextComposite;
import by.talstaya.task02.parser.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParserTest {

    private WordParser wordParser = new WordParser();
    private LexemeParser lexemeParser = new LexemeParser(wordParser);
    private SentenceParser sentenceParser = new SentenceParser(lexemeParser);
    private ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
    private TextParser textParser = new TextParser(paragraphParser);

    private static final String TEXT = "    It has survived, remaining essentially unchanged. It was popularised. \n" +
            "    It is a long established fact. It has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here'. \n" +
            "    A a reader will be, yes. \n" +
            "    Bye. ";

    private static final String[] PARAGRAPHS = {
            "It has survived, remaining essentially unchanged. It was popularised. ",
            "It is a long established fact. It has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here'. ",
            "A a reader will be, yes. ",
            "Bye. "
    };

    private static final String[] SENTENCES = {
            "It has survived, remaining essentially unchanged. ",
            "It was popularised. ",
            "It is a long established fact. ",
            "It has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here'. ",
            "A a reader will be, yes. ",
            "Bye. "
    };

    private static final String[] LEXEMES = {
            "fact.",
            "more-or-less",
            "'Content"
    };

    private static final String[] WORDS = {
            "fact",
            "more",
            "or",
            "less",
            "Content"
    };

    @DataProvider
    public Object[] text() {
        return new Object[]{
                TEXT
        };
    }

    @DataProvider
    public Object[] paragraphs() {
        return new Object[] {
                PARAGRAPHS[0],
                PARAGRAPHS[1],
                PARAGRAPHS[2],
                PARAGRAPHS[3]
        };
    }

    @DataProvider
    public Object[] sentences() {
        return new Object[] {
                SENTENCES[0],
                SENTENCES[1],
                SENTENCES[2],
                SENTENCES[3],
                SENTENCES[4],
                SENTENCES[5]
        };
    }

    @DataProvider
    public Object[] lexemes() {
        return new Object[] {
                LEXEMES[0],
                LEXEMES[1],
                LEXEMES[2],
        };
    }

    @DataProvider
    public Object[] words() {
        return new Object[] {
                WORDS[0],
                WORDS[1],
                WORDS[2],
                WORDS[3],
                WORDS[4]
        };
    }

    @Test(dataProvider = "text")
    public void testTextParser(String expected) {
        TextComponent textComponent = new TextComposite(textParser.parseData(expected), TextComponent.ComponentType.TEXT);
        String text = textComponent.toString();
        Assert.assertEquals(text, expected);
    }

    @Test(dataProvider = "paragraphs")
    public void testParagraphParser(String expected) {
        TextComponent textComponent = new TextComposite(paragraphParser.parseData(expected), TextComponent.ComponentType.PARAGRAPH);
        String text = textComponent.toString();
        Assert.assertEquals(text, expected);
    }

    @Test(dataProvider = "sentences")
    public void testSentencesParser(String expected) {
        TextComponent textComponent = new TextComposite(sentenceParser.parseData(expected), TextComponent.ComponentType.SENTENCE);
        String text = textComponent.toString();
        Assert.assertEquals(text, expected);
    }

    @Test(dataProvider = "lexemes")
    public void testLexemesParser(String expected) {
        TextComponent textComponent = new TextComposite(lexemeParser.parseData(expected), TextComponent.ComponentType.LEXEME);
        String text = textComponent.toString();
        Assert.assertEquals(text, expected);
    }

    @Test(dataProvider = "words")
    public void testWordsParser(String expected) {
        TextComponent textComponent = new TextComposite(wordParser.parseData(expected), TextComponent.ComponentType.WORD);
        String text = textComponent.toString();
        Assert.assertEquals(text, expected);
    }
}
