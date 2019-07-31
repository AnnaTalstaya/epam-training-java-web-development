package by.talstaya.task02.parser;

import by.talstaya.task02.component.SymbolLeaf;
import by.talstaya.task02.component.TextComponent;
import by.talstaya.task02.component.TextComposite;

import java.util.ArrayList;
import java.util.List;

public class TextParser extends DataParser {

    private final String PARAGRAPH_SEPARATOR = "[ ]{4}";

    public TextParser(ParagraphParser paragraphParser) {
        this.nextParser = paragraphParser;
    }

    @Override
    public List<TextComponent> parseData(String data) {
        String[] paragraphs =  data.split(PARAGRAPH_SEPARATOR);

        List<TextComponent> textComponents = new ArrayList<>();

        for (int i = 1; i < paragraphs.length; i++) {
            TextComponent textComponent;

            if (nextParser != null) {
                textComponent = new TextComposite(nextParser.parseData(paragraphs[i]), TextComponent.ComponentType.PARAGRAPH);
            } else {
                textComponent = new SymbolLeaf(paragraphs[i], TextComponent.ComponentType.PARAGRAPH);
            }
            textComponents.add(textComponent);
        }

        return textComponents;
    }
}


