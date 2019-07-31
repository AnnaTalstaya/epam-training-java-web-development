package by.talstaya.task01.entity;

import by.talstaya.task01.util.DeveloperIdGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Developer extends Employee {

    public enum DeveloperStatus {
        INTERN("INTERN", 0),
        JUNIOR("JUNIOR", 1),
        MIDDLE("MIDDLE", 2),
        SENIOR("SENIOR", 3),
        LEAD("LEAD", 4),
        CHIEF("CHIEF", 5);

        private String status;
        private int index;


        DeveloperStatus(String status, int index) {
            this.status = status;
            this.index = index;
        }

        public String getStatus() {
            return status;
        }

        public int getIndex() {
            return index;
        }
    }

    private DeveloperStatus developerStatus;

    public Developer(long developerId, String name, String surname, BigDecimal salaryPerHour, LocalDate employmentDate, DeveloperStatus developerStatus) {
        super(developerId, name, surname, salaryPerHour, employmentDate);
        this.developerStatus = developerStatus;
    }

    public Developer(String name, String surname, BigDecimal salaryPerHour, LocalDate employmentDate, DeveloperStatus developerStatus) {
        super(name, surname, salaryPerHour, employmentDate);
        this.setEmployeeId(DeveloperIdGenerator.generateId());
        this.developerStatus = developerStatus;
    }

    public DeveloperStatus getDeveloperStatus() {
        return developerStatus;
    }

    public void setDeveloperStatus(DeveloperStatus developerStatus) {
        this.developerStatus = developerStatus;
    }

    @Override
    public String toString() {
        return "Developer{" +
                super.toString() +
                ", developerStatus=" + developerStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Developer)) return false;
        if (!super.equals(o)) return false;
        Developer developer = (Developer) o;
        return developerStatus == developer.developerStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), developerStatus);
    }
}
