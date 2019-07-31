package by.talstaya.task05.entity;

import java.util.Objects;

public class Plant {
    private String id;
    private String name;
    private String soil;
    private Origin origin;
    private VisualParameter visualParameter;
    private GrowingTip growingTip;
    private String multiplying = "";

    public Plant() {
        this.origin = new Origin();
        this.visualParameter = new VisualParameter();
        this.growingTip = new GrowingTip();
    }

    public Plant(String id, String name, String soil, Origin origin, VisualParameter visualParameter, GrowingTip growingTip, String multiplying) {
        this.id = id;
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.visualParameter = visualParameter;
        this.growingTip = growingTip;
        this.multiplying = multiplying;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public VisualParameter getVisualParameter() {
        return visualParameter;
    }

    public void setVisualParameter(VisualParameter visualParameter) {
        this.visualParameter = visualParameter;
    }

    public GrowingTip getGrowingTip() {
        return growingTip;
    }

    public void setGrowingTip(GrowingTip growingTip) {
        this.growingTip = growingTip;
    }

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public String toString() {
        return "Plant{" + '\n' +
                "id=" + id + '\n' +
                "name=" + name + '\n' +
                "soil=" + soil + '\n' +
                "origin=" + origin + '\n' +
                "visualParameter=" + visualParameter + '\n' +
                "growingTip=" + growingTip + '\n' +
                "multiplying=" + multiplying
                + "}" + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plant)) return false;
        Plant plant = (Plant) o;
        return Objects.equals(id, plant.id) &&
                Objects.equals(name, plant.name) &&
                Objects.equals(soil, plant.soil) &&
                Objects.equals(origin, plant.origin) &&
                Objects.equals(visualParameter, plant.visualParameter) &&
                Objects.equals(growingTip, plant.growingTip) &&
                Objects.equals(multiplying, plant.multiplying);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, soil, origin, visualParameter, growingTip, multiplying);
    }

}
