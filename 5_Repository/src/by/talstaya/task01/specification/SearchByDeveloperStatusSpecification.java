package by.talstaya.task01.specification;

import by.talstaya.task01.entity.Developer;
import by.talstaya.task01.entity.Employee;

public class SearchByDeveloperStatusSpecification implements Specification {
    private Developer.DeveloperStatus developerStatus;

    public SearchByDeveloperStatusSpecification(Developer.DeveloperStatus developerStatus) {
        this.developerStatus = developerStatus;
    }

    @Override
    public boolean test(Employee employee) {
        Developer developer = (Developer)employee;
        return developerStatus == developer.getDeveloperStatus();
    }
}
