package by.talstaya.task01.specification;

import by.talstaya.task01.entity.Employee;

import java.math.BigDecimal;

public class RaiseTheSalarySpecification implements Specification {

    private BigDecimal deltaSalary;

    public RaiseTheSalarySpecification(BigDecimal deltaSalary) {
        this.deltaSalary = deltaSalary;
    }

    @Override
    public boolean test(Employee employee) {
        employee.setSalaryPerHour(employee.getSalaryPerHour().add(deltaSalary));
        return true;
    }
}
