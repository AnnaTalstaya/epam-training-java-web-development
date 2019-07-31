package by.talstaya.task02.component;

import java.util.List;

public class SymbolLeaf implements TextComponent {

    private String symbol;
    private ComponentType componentType;

    public SymbolLeaf(String symbol, ComponentType componentType) {
        this.symbol = symbol;
        this.componentType = componentType;
    }

    @Override
    public List<TextComponent> getTextComponents() {
        return null;
    }

    @Override
    public void add(TextComponent textComponent) {

    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }

    public String getSymbol() {
        return symbol;
    }

}
