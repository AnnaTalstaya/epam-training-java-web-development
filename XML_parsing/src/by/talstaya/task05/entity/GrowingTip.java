package by.talstaya.task05.entity;

import java.util.Objects;

public class GrowingTip {

    private int temperature;
    private boolean light;
    private int water;

    public GrowingTip() {
    }

    public GrowingTip(int temperature, boolean light, int water) {
        this.temperature = temperature;
        this.light = light;
        this.water = water;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    @Override
    public String toString() {
        return "GrowingTip{" +
                "temperature=" + temperature +
                ", light=" + light +
                ", water=" + water +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrowingTip)) return false;
        GrowingTip that = (GrowingTip) o;
        return temperature == that.temperature &&
                light == that.light &&
                water == that.water;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, light, water);
    }
}
