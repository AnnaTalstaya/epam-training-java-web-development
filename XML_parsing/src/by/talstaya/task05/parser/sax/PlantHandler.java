package by.talstaya.task05.parser.sax;

import by.talstaya.task05.entity.Plant;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class PlantHandler extends DefaultHandler {

    private Set<Plant> flowers;
    private Plant current = null;
    private PlantEnum currentEnum = null;
    private EnumSet<PlantEnum> withText;

    public PlantHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(PlantEnum.COUNTRY, PlantEnum.WATER);
    }

    public Set<Plant> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("plant".equals(localName)) {
            current = new Plant();
            current.setId(attributes.getValue(0));
            current.setName(attributes.getValue(1));
            current.setSoil(attributes.getValue(2));

            if (attributes.getLength() == 4) {
                current.setMultiplying(attributes.getValue(3));
            }
        } else {
            PlantEnum temp = PlantEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("plant".equals(localName)) {
            flowers.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case COUNTRY:
                    current.getOrigin().setCountry(s);
                    break;
                case CULTIVATED:
                    current.getOrigin().setCultivated(s);
                    break;
                case COLORSTEM:
                    current.getVisualParameter().setColorStem(s);
                    break;
                case COLORLEAF:
                    current.getVisualParameter().setColorLeaf(s);
                    break;
                case AVERAGESIZE:
                    current.getVisualParameter().setAverageSize(Integer.parseInt(s));
                    break;
                case TEMPERATURE:
                    current.getGrowingTip().setTemperature(Integer.parseInt(s));
                    break;
                case LIGHT:
                    current.getGrowingTip().setLight(Boolean.parseBoolean(s));
                    break;
                case WATER:
                    current.getGrowingTip().setWater(Integer.parseInt(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
