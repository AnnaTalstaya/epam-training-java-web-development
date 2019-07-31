package by.talstaya.task02.run;

import by.talstaya.task02.component.TextComponent;
import by.talstaya.task02.component.TextComposite;
import by.talstaya.task02.exception.CustomException;
import by.talstaya.task02.parser.*;
import by.talstaya.task02.reader.FileReader;
import by.talstaya.task02.sort.DataSort;
import by.talstaya.task02.sort.ParagraphSortByNumberOfSentences;
import by.talstaya.task02.sort.SentenceSortByNumberOfWords;
import by.talstaya.task02.sort.WordSortByLength;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger("name");

    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        String data;

        try {
            data = fileReader.readData(new File("data/input.txt"));

            WordParser wordParser = new WordParser();
            LexemeParser lexemeParser = new LexemeParser(wordParser);
            SentenceParser sentenceParser = new SentenceParser(lexemeParser);
            ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
            TextParser textParser = new TextParser(paragraphParser);

            TextComponent textComposite = new TextComposite(textParser.parseData(data), TextComponent.ComponentType.TEXT);

            LOGGER.info("=======================Recovered text:=======================");
            LOGGER.info(textComposite.toString());


            LOGGER.info("==========Sorted paragraphs by number of sentences:==========");
            DataSort paragraphSort = new ParagraphSortByNumberOfSentences();
            List<TextComponent> textComponentList = paragraphSort.sort(textComposite);
            textComponentList.forEach(paragraph -> {
                LOGGER.info(paragraph.toString());
            });

            LOGGER.info("============Sorted words by length in sentences:=============");
            DataSort sortWords = new WordSortByLength();
            List<TextComponent> sentencesWithSortedWordsList = sortWords.sort(textComposite);
            sentencesWithSortedWordsList.forEach(sentence -> {
                LOGGER.info(sentence.toString());
            } );

            textComposite = new TextComposite(textParser.parseData(data), TextComponent.ComponentType.TEXT);
            LOGGER.info("======Sorted sentences in paragraphs by number of words:======");
            DataSort sortSentences = new SentenceSortByNumberOfWords();
            List<TextComponent> paragraphsWithSortedSentences = sortSentences.sort(textComposite);
            paragraphsWithSortedSentences.forEach(paragraph -> {
                LOGGER.info(paragraph.toString());
            });


        } catch (IOException e) {
            LOGGER.error("There's a problem with file");
        }
    }

}
