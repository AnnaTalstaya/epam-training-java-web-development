package by.talstaya.task02.component;

import by.talstaya.task02.exception.CustomException;
import by.talstaya.task02.parser.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TextComposite implements TextComponent {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private List<TextComponent> textComponents;
    private ComponentType componentType;

    public TextComposite(final List<TextComponent> textComponents, final ComponentType componentType) {
        this.textComponents = textComponents;
        this.componentType = componentType;
    }

    @Override
    public List<TextComponent> getTextComponents() {
        return textComponents;
    }

    @Override
    public void add(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (TextComponent textComponent : textComponents) {
            switch (textComponent.getComponentType()) {
                case PARAGRAPH:
                    if (textComponents.indexOf(textComponent) != textComponents.size()-1) {
                        stringBuilder.append("    ".concat(textComponent.toString().concat("\n")));
                    } else {
                        stringBuilder.append("    ".concat(textComponent.toString()));
                    }
                    break;
                case SENTENCE:
                    stringBuilder.append(textComponent.toString());
                    break;
                case LEXEME:
                    stringBuilder.append(textComponent.toString().concat(" "));
                    break;
                case WORD:
                    stringBuilder.append(textComponent.toString());
                    break;
                case SYMBOL:
                    SymbolLeaf symbolLeaf = (SymbolLeaf) textComponent;
                    stringBuilder.append(symbolLeaf.getSymbol());
                    break;
                default:
                    LOGGER.error("Incorrect type of textComponent!");
            }
        }
        return stringBuilder.toString();
    }
}
