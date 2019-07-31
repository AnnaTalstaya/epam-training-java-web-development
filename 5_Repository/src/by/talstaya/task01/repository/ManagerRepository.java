package by.talstaya.task01.repository;

import by.talstaya.task01.entity.Manager;
import by.talstaya.task01.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerRepository {
    private List<Manager> managerList = new ArrayList<>();

    public boolean add(Manager manager) {
        return managerList.add(manager);
    }

    public boolean remove(Manager manager) {
        return managerList.remove(manager);
    }

    public List<Manager> get() {
        return managerList;
    }

    public List<Manager> sort(Comparator<? super Manager> comparator) {
        managerList.sort(comparator);
        return managerList;
    }

    public List<Manager> query(Specification... specifications) {
        for (Specification specification : specifications){
            managerList = managerList.stream().filter(specification).collect(Collectors.toList());
        }
        return  managerList;
    }
}
