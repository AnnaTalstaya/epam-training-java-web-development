package by.talstaya.task01.specification;

import by.talstaya.task01.entity.Employee;
import by.talstaya.task01.entity.Manager;

public class SearchByManagerNameOfProject implements Specification {

    private String nameOfProject;

    public SearchByManagerNameOfProject(String nameOfProject) {
        this.nameOfProject = nameOfProject;
    }

    @Override
    public boolean test(Employee employee) {
        return nameOfProject.equals(((Manager)employee).getNameOfProject());
    }
}
