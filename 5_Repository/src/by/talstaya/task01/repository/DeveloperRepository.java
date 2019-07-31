package by.talstaya.task01.repository;

import by.talstaya.task01.entity.Developer;
import by.talstaya.task01.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeveloperRepository {
    private List<Developer> developers = new ArrayList<>();

    public boolean add(Developer developer) {
        return developers.add(developer);
    }

    public boolean remove(Developer developer) {
        return developers.remove(developer);
    }

    public List<Developer> get() {
        return developers;
    }

    public List<Developer> sort(Comparator<? super Developer> comparator) {
        developers.sort(comparator);
        return developers;
    }

    public List<Developer> query(Specification... specifications) {
        for (Specification specification : specifications){
            developers = developers.stream().filter(specification).collect(Collectors.toList());
        }
        return  developers;
    }


}
