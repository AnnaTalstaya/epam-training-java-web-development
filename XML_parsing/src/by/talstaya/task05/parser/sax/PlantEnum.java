package by.talstaya.task05.parser.sax;

public enum PlantEnum {
    FLOWERS("flowers"),
    PLANT("plant"),
    ID("id"),
    NAME("name"),
    SOIL("soil"),
    MULTIPLYING("multiplying"),
    COUNTRY("country"),
    CULTIVATED("cultivated"),
    COLORSTEM("colorStem"),
    COLORLEAF("colorLeaf"),
    AVERAGESIZE("averageSize"),
    TEMPERATURE("temperature"),
    LIGHT("light"),
    WATER("water"),
    GROWINGTIP("growingTip"),
    VISUALPARAMETER("visualParameter"),
    ORIGIN("origin");



    private String value;

    PlantEnum(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }


}
