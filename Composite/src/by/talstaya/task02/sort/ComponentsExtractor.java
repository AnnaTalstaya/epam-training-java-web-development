package by.talstaya.task02.sort;

import by.talstaya.task02.component.SymbolLeaf;
import by.talstaya.task02.component.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class ComponentsExtractor {

    private List<TextComponent> textComponentList = new ArrayList<>();

    public List<TextComponent> extractComponents(final TextComponent root, final TextComponent.ComponentType componentType) {
        if (root.getComponentType() == componentType) {
            textComponentList.add(root);
        } else {
            if (!(root instanceof SymbolLeaf)) {
                root.getTextComponents().forEach(textComponent -> {
                    extractComponents(textComponent, componentType);
                });
            }
        }
        return textComponentList;
    }
}
