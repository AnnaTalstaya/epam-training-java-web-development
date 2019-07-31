package by.talstaya.task01.comparator;

import by.talstaya.task01.entity.Employee;

import java.util.Comparator;

public class EmployeeSurnameComparator implements Comparator<Employee> {
    @Override
    public int compare(final Employee emp1, final Employee emp2) {
        return emp1.getSurname().compareTo(emp2.getSurname());
    }
}
