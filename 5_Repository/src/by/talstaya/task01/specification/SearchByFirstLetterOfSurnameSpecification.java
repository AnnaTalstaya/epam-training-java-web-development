package by.talstaya.task01.specification;

import by.talstaya.task01.entity.Employee;

public class SearchByFirstLetterOfSurnameSpecification implements Specification {

    private String firstLetterOfSurname;

    public SearchByFirstLetterOfSurnameSpecification(String firstLetterOfSurname) {
        this.firstLetterOfSurname = firstLetterOfSurname;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getSurname().startsWith(firstLetterOfSurname);
    }
}
