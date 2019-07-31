package by.talstaya.task05.parser;

import by.talstaya.task05.entity.Plant;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public abstract class FlowersAbstractBuilder {

    private Set<Plant> flowers;

    protected FlowersAbstractBuilder() {
        this.flowers = new HashSet<>();
    }

    public Set<Plant> getFlowers() {
        return flowers;
    }

    public abstract void buildSetPlants(String filename) throws IOException, SAXException, XMLStreamException;
}
