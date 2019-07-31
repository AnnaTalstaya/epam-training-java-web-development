package by.talstaya.task01.specification;

import by.talstaya.task01.entity.Employee;

public class SearchByNameSpecification implements Specification {

    private String name;

    public SearchByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Employee employee) {
        return name.equals(employee.getName());
    }
}
