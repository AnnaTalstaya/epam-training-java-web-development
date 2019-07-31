package by.talstaya.task02.parser;

import by.talstaya.task02.component.SymbolLeaf;
import by.talstaya.task02.component.TextComponent;
import by.talstaya.task02.component.TextComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends DataParser {

    private final String REGEX_LEXEME = "[^\\s.?!]+([,.?!]|\\.{3})?";

    public SentenceParser(LexemeParser lexemeParser) {
        this.nextParser = lexemeParser;
    }

    @Override
    public List<TextComponent> parseData(String data) {
        Pattern pattern = Pattern.compile(REGEX_LEXEME);
        Matcher matcher = pattern.matcher(data);

        List<TextComponent> textComponents = new ArrayList<>();
        TextComponent textComponent;
        while (matcher.find()) {
            if (nextParser != null) {
                textComponent = new TextComposite(nextParser.parseData(matcher.group()), TextComponent.ComponentType.LEXEME);
            } else {
                textComponent = new SymbolLeaf(matcher.group(), TextComponent.ComponentType.LEXEME);
            }
            textComponents.add(textComponent);
        }

        return textComponents;
    }
}
