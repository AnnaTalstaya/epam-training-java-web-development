package by.talstaya.task01.specification;

import by.talstaya.task01.entity.Employee;

public class SearchBySurnameSpecification implements Specification {

    private String surname;

    public SearchBySurnameSpecification(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean test(Employee employee) {
        return surname.equals(employee.getSurname());
    }
}
