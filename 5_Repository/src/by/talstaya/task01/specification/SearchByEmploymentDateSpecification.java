package by.talstaya.task01.specification;

import by.talstaya.task01.entity.Employee;

import java.time.LocalDate;

public class SearchByEmploymentDateSpecification implements Specification {

    private LocalDate date;

    public SearchByEmploymentDateSpecification(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Employee employee) {
        return date.equals(employee.getEmploymentDate());
    }
}
