package by.talstaya.task05.entity;

import java.util.Objects;

public class Origin {
    private String country;
    private String cultivated;

    public Origin() {
    }

    public Origin(String country, String cultivated) {
        this.country = country;
        this.cultivated = cultivated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCultivated() {
        return cultivated;
    }

    public void setCultivated(String cultivated) {
        this.cultivated = cultivated;
    }

    @Override
    public String toString() {
        return "Origin: ".concat(this.country).concat(" ").concat(this.cultivated);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Origin)) return false;
        Origin origin = (Origin) o;
        return Objects.equals(country, origin.country) &&
                Objects.equals(cultivated, origin.cultivated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, cultivated);
    }
}
