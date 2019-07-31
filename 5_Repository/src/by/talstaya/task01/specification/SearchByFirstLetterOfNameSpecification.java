package by.talstaya.task01.specification;

import by.talstaya.task01.entity.Employee;

public class SearchByFirstLetterOfNameSpecification implements Specification {

    private String firstLetterOfName;

    public SearchByFirstLetterOfNameSpecification(String firstLetterOfName) {
        this.firstLetterOfName = firstLetterOfName;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getName().startsWith(firstLetterOfName);
    }
}
