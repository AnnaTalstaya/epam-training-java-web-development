package by.talstaya.task05.parser;

import by.talstaya.task05.parser.dom.FlowersDOMBuilder;
import by.talstaya.task05.parser.sax.FlowersSAXBuilder;
import by.talstaya.task05.parser.stax.FlowersStAXBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class ParserBuilder {

    public enum ParserType {
        DOM,
        SAX,
        StAX
    }

    public FlowersAbstractBuilder createParser(ParserType parserType) throws ParserConfigurationException, SAXException {
        switch (parserType) {
            case DOM:
                return new FlowersDOMBuilder();
            case SAX:
                return new FlowersSAXBuilder();
            case StAX:
                return new FlowersStAXBuilder();
            default:
                throw new IllegalArgumentException("There's no such parser");
        }
    }
}
