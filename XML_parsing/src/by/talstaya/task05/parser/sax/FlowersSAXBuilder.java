package by.talstaya.task05.parser.sax;

import by.talstaya.task05.entity.Plant;
import by.talstaya.task05.parser.FlowersAbstractBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class FlowersSAXBuilder extends FlowersAbstractBuilder {

    private Set<Plant> flowers;
    private PlantHandler plantHandler;
    private SAXParser parser;

    public FlowersSAXBuilder() throws SAXException, ParserConfigurationException {
        plantHandler = new PlantHandler();

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setNamespaceAware(true);
        parser = parserFactory.newSAXParser();

    }

    @Override
    public Set<Plant> getFlowers() {
        return flowers;
    }

    @Override
    public void buildSetPlants(String filename) throws IOException, SAXException {
        parser.parse(filename, plantHandler);
        flowers = plantHandler.getFlowers();
    }
}
