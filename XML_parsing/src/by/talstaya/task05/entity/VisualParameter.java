package by.talstaya.task05.entity;

import java.util.Objects;

public class VisualParameter {

    private String colorStem;
    private String colorLeaf;
    private int averageSize;

    public VisualParameter() {
    }

    public VisualParameter(String colorStem, String colorLeaf, int averageSize) {
        this.colorStem = colorStem;
        this.colorLeaf = colorLeaf;
        this.averageSize = averageSize;
    }

    public String getColorStem() {
        return colorStem;
    }

    public void setColorStem(String colorStem) {
        this.colorStem = colorStem;
    }

    public String getColorLeaf() {
        return colorLeaf;
    }

    public void setColorLeaf(String colorLeaf) {
        this.colorLeaf = colorLeaf;
    }

    public int getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(int averageSize) {
        this.averageSize = averageSize;
    }

    @Override
    public String toString() {
        return "VisualParameter: ".concat(this.colorStem).concat(" ").concat(this.colorLeaf).
                concat(" ").concat(String.valueOf(this.averageSize));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisualParameter)) return false;
        VisualParameter that = (VisualParameter) o;
        return averageSize == that.averageSize &&
                Objects.equals(colorStem, that.colorStem) &&
                Objects.equals(colorLeaf, that.colorLeaf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorStem, colorLeaf, averageSize);
    }
}
