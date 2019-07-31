package by.talstaya.task02.component;

import java.util.List;

public interface TextComponent {

    public enum ComponentType {
        TEXT,
        PARAGRAPH,
        SENTENCE,
        LEXEME,
        WORD,
        SYMBOL
    }

    List<TextComponent> getTextComponents();

    void add(TextComponent textComponent);

    ComponentType getComponentType();
}
