package by.talstaya.task01.specification;

import by.talstaya.task01.entity.Employee;

import java.math.BigDecimal;

public class SearchBySalaryPerHourSpecification implements Specification {

    private BigDecimal minSalaryPerHour;
    private BigDecimal maxSalaryPerHour;

    public SearchBySalaryPerHourSpecification(BigDecimal minSalaryPerHour, BigDecimal maxSalaryPerHour) {
        this.minSalaryPerHour = minSalaryPerHour;
        this.maxSalaryPerHour = maxSalaryPerHour;
    }

    @Override
    public boolean test(Employee employee) {
        BigDecimal employeeSalaryPerHour = employee.getSalaryPerHour();

        return employeeSalaryPerHour.compareTo(minSalaryPerHour) >=0 && employeeSalaryPerHour.compareTo(maxSalaryPerHour) <= 0;
    }
}
