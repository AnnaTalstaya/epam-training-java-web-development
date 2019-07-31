package by.talstaya.task01.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Employee {
    private long employeeId;
    private String name;
    private String surname;
    private BigDecimal salaryPerHour;
    private LocalDate employmentDate;

    public Employee(long employeeId, String name, String surname, BigDecimal salaryPerHour, LocalDate employmentDate) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.salaryPerHour = salaryPerHour;
        this.employmentDate = employmentDate;
    }

    public Employee(String name, String surname, BigDecimal salaryPerHour, LocalDate employmentDate) {
        this.name = name;
        this.surname = surname;
        this.salaryPerHour = salaryPerHour;
        this.employmentDate = employmentDate;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salaryPerHour=" + salaryPerHour +
                ", employmentDate=" + employmentDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return  name.equals(employee.name) &&
                surname.equals(employee.surname) &&
                salaryPerHour.equals(employee.salaryPerHour) &&
                employmentDate.equals(employee.employmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, salaryPerHour, employmentDate);
    }
}
