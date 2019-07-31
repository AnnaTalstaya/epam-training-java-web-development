package by.talstaya.task02.parser;

import by.talstaya.task02.component.TextComponent;
import by.talstaya.task02.component.TextComposite;
import by.talstaya.task02.component.SymbolLeaf;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends DataParser {

    private final String REGEX_WORD  = "[\'\",]|[-]|\\w+";
    private final String REGEX_END_OF_SENTENCE = "[.?!]|\\.{3}";

    public LexemeParser(WordParser wordParser) {
        this.nextParser = wordParser;
    }

    @Override
    public List<TextComponent> parseData(String data) {
        Pattern patternWord = Pattern.compile(REGEX_WORD);
        Matcher matcherWord = patternWord.matcher(data);

        Pattern patternEndOfSentence = Pattern.compile(REGEX_END_OF_SENTENCE);
        Matcher matcherEndOfSentence = patternEndOfSentence.matcher(data);

        List<TextComponent> textComponents = new ArrayList<>();

        while (matcherWord.find()) {
            TextComponent textComponent;
            if (matcherWord.group().equals("\'") || matcherWord.group().equals("\"") || matcherWord.group().equals("-") || matcherWord.group().equals(",")) {
                textComponent = new SymbolLeaf(matcherWord.group(), TextComponent.ComponentType.SYMBOL);
            } else if (nextParser != null) {
                textComponent = new TextComposite(nextParser.parseData(matcherWord.group()), TextComponent.ComponentType.WORD);
            } else {
                textComponent = new SymbolLeaf(matcherWord.group(), TextComponent.ComponentType.WORD);
            }

            textComponents.add(textComponent);
        }

        if (matcherEndOfSentence.find()) {
            TextComponent textComponent = new SymbolLeaf(matcherEndOfSentence.group(), TextComponent.ComponentType.SYMBOL);
            textComponents.add(textComponent);
        }

        return textComponents;
    }


}
