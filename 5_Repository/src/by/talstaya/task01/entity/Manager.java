package by.talstaya.task01.entity;

import by.talstaya.task01.util.ManagerIdGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Manager extends Employee{

    private String nameOfProject;

    public Manager(long managerId, String name, String surname, BigDecimal salaryPerHour, LocalDate employmentDate, String nameOfProject) {
        super(managerId, name, surname, salaryPerHour, employmentDate);
        this.nameOfProject = nameOfProject;
    }

    public Manager(String name, String surname, BigDecimal salaryPerHour, LocalDate employmentDate, String nameOfProject) {
        super(name, surname, salaryPerHour, employmentDate);
        this.setEmployeeId(ManagerIdGenerator.generateId());
        this.nameOfProject = nameOfProject;
    }

    public String getNameOfProject() {
        return nameOfProject;
    }

    public void setNameOfProject(String nameOfProject) {
        this.nameOfProject = nameOfProject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return nameOfProject.equals(manager.nameOfProject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameOfProject);
    }
}
