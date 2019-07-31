package tests.parser;

import by.talstaya.task05.entity.GrowingTip;
import by.talstaya.task05.entity.Origin;
import by.talstaya.task05.entity.Plant;
import by.talstaya.task05.entity.VisualParameter;
import by.talstaya.task05.parser.FlowersAbstractBuilder;
import by.talstaya.task05.parser.ParserBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static by.talstaya.task05.parser.ParserBuilder.ParserType.SAX;

public class FlowersSAXBuilderTest {
    private Set<Plant> flowers = new HashSet<>();

    @Test
    public void testDOMParser() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        ParserBuilder parserBuilder = new ParserBuilder();
        FlowersAbstractBuilder abstractBuilder = parserBuilder.createParser(SAX);
        abstractBuilder.buildSetPlants("data/greenhouse_test.xml");
        Set<Plant> actual = abstractBuilder.getFlowers();

        flowers.add(new Plant("A1", "alstroemeria", "podzolic", new Origin("Spain", "1322-11-25"), new VisualParameter("green", "red", 30), new GrowingTip(20, true, 100), "leafs"));
        flowers.add(new Plant("A2", "aster", "unpaved", new Origin("Russia", "1986-03-02"), new VisualParameter("white", "green", 15), new GrowingTip(15, false, 70), "seeds"));
        flowers.add(new Plant("A3", "begonia", "sod-podzolic", new Origin("Scotland", "1732-08-10"), new VisualParameter("green", "green", 100), new GrowingTip(10, false, 250), "seeds"));

        Assert.assertEquals(actual, flowers);
    }
}
