package by.talstaya.task05.entity;

import java.util.ArrayList;
import java.util.List;

public class Flowers {
    private List<Plant> plantList = new ArrayList<>();

    public Flowers() {
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
    }

    public void add(Plant plant) {
        plantList.add(plant);
    }

    @Override
    public String toString() {
        return "Flowers{" +
                "plantList=" + plantList +
                '}';
    }
}
