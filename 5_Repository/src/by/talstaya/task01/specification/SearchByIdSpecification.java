package by.talstaya.task01.specification;

import by.talstaya.task01.entity.Employee;

public class SearchByIdSpecification implements Specification {

    private long id;

    public SearchByIdSpecification(long id) {
        this.id = id;
    }

    @Override
    public boolean test(Employee employee) {
        return id == employee.getEmployeeId();
    }
}
