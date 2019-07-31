package by.talstaya.task05.parser.stax;

import by.talstaya.task05.entity.GrowingTip;
import by.talstaya.task05.entity.Origin;
import by.talstaya.task05.entity.Plant;
import by.talstaya.task05.entity.VisualParameter;
import by.talstaya.task05.parser.FlowersAbstractBuilder;
import by.talstaya.task05.parser.sax.PlantEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class FlowersStAXBuilder extends FlowersAbstractBuilder {

    private Set<Plant> flowers;
    private XMLInputFactory inputFactory;

    public FlowersStAXBuilder() {
        flowers = new HashSet<>();
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public Set<Plant> getFlowers() {
        return flowers;
    }

    @Override
    public void buildSetPlants(String fileName) throws FileNotFoundException, XMLStreamException {
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
        String name;

        while (reader.hasNext()) {
            int type = reader.next();

            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();

                if (PlantEnum.valueOf(name.toUpperCase()) == PlantEnum.PLANT) {
                    Plant plant = buildPlant(reader);
                    flowers.add(plant);
                }
            }
        }
    }

    private Plant buildPlant(XMLStreamReader reader) throws XMLStreamException {
        Plant plant = new Plant();

        plant.setId(reader.getAttributeValue(null, PlantEnum.ID.getValue()));
        plant.setName(reader.getAttributeValue(null, PlantEnum.NAME.getValue()));
        plant.setSoil(reader.getAttributeValue(null, PlantEnum.SOIL.getValue()));
        if (reader.getAttributeValue(null, PlantEnum.MULTIPLYING.getValue()) != null) {
            plant.setMultiplying(reader.getAttributeValue(null, PlantEnum.MULTIPLYING.getValue()));
        }

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantEnum.valueOf(name.toUpperCase())) {
                        case ORIGIN:
                            plant.setOrigin(getXMlOrigin(reader));
                            break;
                        case VISUALPARAMETER:
                            plant.setVisualParameter(getXMlVisual(reader));
                            break;
                        case GROWINGTIP:
                            plant.setGrowingTip(getXMlGrowingTip(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantEnum.valueOf(name.toUpperCase()) == PlantEnum.PLANT) {
                        return plant;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Plant");
    }

    private Origin getXMlOrigin(XMLStreamReader reader) throws XMLStreamException {
        Origin origin = new Origin();
        int type;
        String name;

        while (reader.hasNext()) {
            type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantEnum.valueOf(name.toUpperCase())) {
                        case COUNTRY:
                            origin.setCountry(getXMLText(reader));
                            break;
                        case CULTIVATED:
                            origin.setCultivated(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantEnum.valueOf(name.toUpperCase()) == PlantEnum.ORIGIN) {
                        return origin;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Origin");
    }

    private VisualParameter getXMlVisual(XMLStreamReader reader) throws XMLStreamException {
        VisualParameter visual = new VisualParameter();
        int type;
        String name;

        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantEnum.valueOf(name.toUpperCase())) {
                        case COLORSTEM:
                            visual.setColorStem(getXMLText(reader));
                            break;
                        case COLORLEAF:
                            visual.setColorLeaf(getXMLText(reader));
                            break;
                        case AVERAGESIZE:
                            visual.setAverageSize(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantEnum.valueOf(name.toUpperCase()) == PlantEnum.VISUALPARAMETER) {
                        return visual;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag VisualParameter");
    }

    private GrowingTip getXMlGrowingTip(XMLStreamReader reader) throws XMLStreamException {
        GrowingTip growingTip = new GrowingTip();
        int type;
        String name;

        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantEnum.valueOf(name.toUpperCase())) {
                        case TEMPERATURE:
                            growingTip.setTemperature(Integer.parseInt(getXMLText(reader)));
                            break;
                        case LIGHT:
                            growingTip.setLight(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case WATER:
                            growingTip.setWater(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantEnum.valueOf(name.toUpperCase()) == PlantEnum.GROWINGTIP) {
                        return growingTip;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag GrowingTip");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
