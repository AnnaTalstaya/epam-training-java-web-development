package by.talstaya.task05.parser.dom;

import by.talstaya.task05.entity.Flowers;
import by.talstaya.task05.entity.Plant;
import by.talstaya.task05.parser.FlowersAbstractBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FlowersDOMBuilder extends FlowersAbstractBuilder {

    private Set<Plant> flowers;
    private DocumentBuilder documentBuilder;

    public FlowersDOMBuilder() throws ParserConfigurationException {
        this.flowers = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        documentBuilder = factory.newDocumentBuilder();
    }

    @Override
    public Set<Plant> getFlowers() {
        return flowers;
    }

    @Override
    public void buildSetPlants(String filename) throws IOException, SAXException {
        Document document = documentBuilder.parse(filename);

        Element root = document.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("plant");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element plantElement = (Element) nodeList.item(i);
            Plant plant = buildPlant(plantElement);
            flowers.add(plant);
        }

    }

    private Plant buildPlant(Element plantElement) {
        Plant plant = new Plant();

        plant.setId(plantElement.getAttribute("id"));
        plant.setName(plantElement.getAttribute("name"));
        plant.setSoil(plantElement.getAttribute("soil"));
        plant.setMultiplying(plantElement.getAttribute("multiplying"));

        Element originElement = (Element) plantElement.getElementsByTagName("origin").item(0);
        Element visualParameterElement =(Element) plantElement.getElementsByTagName("visualParameter").item(0);
        Element growingTipElement = (Element) plantElement.getElementsByTagName("growingTip").item(0);

        plant.getOrigin().setCountry(takeElementTextContent(originElement, "country"));
        plant.getOrigin().setCultivated(takeElementTextContent(originElement, "cultivated"));

        plant.getVisualParameter().setColorStem(takeElementTextContent(visualParameterElement, "colorStem"));
        plant.getVisualParameter().setColorLeaf(takeElementTextContent(visualParameterElement, "colorLeaf"));
        plant.getVisualParameter().setAverageSize(Integer.parseInt(takeElementTextContent(visualParameterElement, "averageSize")));

        plant.getGrowingTip().setTemperature(Integer.parseInt(takeElementTextContent(growingTipElement, "temperature")));
        plant.getGrowingTip().setLight(Boolean.parseBoolean(takeElementTextContent(growingTipElement, "light")));
        plant.getGrowingTip().setWater(Integer.parseInt(takeElementTextContent(growingTipElement, "water")));

        return plant;
    }

    private static String takeElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
