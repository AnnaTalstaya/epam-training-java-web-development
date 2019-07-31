package by.talstaya.task02.parser;

import by.talstaya.task02.component.SymbolLeaf;
import by.talstaya.task02.component.TextComponent;
import by.talstaya.task02.component.TextComposite;

import java.util.ArrayList;
import java.util.List;

public abstract class DataParser {

    protected DataParser nextParser;

    public DataParser() {
    }

    public DataParser(DataParser nextParser) {
        this.nextParser = nextParser;
    }

    public void setNextParser(DataParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract List<TextComponent> parseData(final String data);

}
