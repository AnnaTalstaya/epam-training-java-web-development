package by.talstaya.task02.parser;

import by.talstaya.task02.component.TextComponent;
import by.talstaya.task02.component.SymbolLeaf;
import by.talstaya.task02.component.TextComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser extends DataParser {

    private final String REGEX_SYMBOL = ".";

    @Override
    public List<TextComponent> parseData(String data) {
        Pattern pattern = Pattern.compile(REGEX_SYMBOL);
        Matcher matcher = pattern.matcher(data);

        List<TextComponent> textComponents = new ArrayList<>();
        while (matcher.find()) {
            TextComponent textComponent = new SymbolLeaf(matcher.group(), TextComponent.ComponentType.SYMBOL);
            textComponents.add(textComponent);
        }
        return textComponents;
    }
}
