package by.talstaya.task01.creator;

import by.talstaya.task01.entity.Employee;
import by.talstaya.task01.entity.Manager;
import by.talstaya.task01.exception.CustomException;
import by.talstaya.task01.validator.LineValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ManagerCreator implements EmployeeCreator {
    @Override
    public Employee factoryMethod(List<String> lineOfFile) throws CustomException {
        LineValidator validator = new LineValidator();

        if (validator.validLine(lineOfFile)) {
            return new Manager(lineOfFile.get(1),
                    lineOfFile.get(2),
                    new BigDecimal(lineOfFile.get(3)),
                    LocalDate.parse(lineOfFile.get(4), DateTimeFormatter.ofPattern("d/MM/yyyy")),
                    lineOfFile.get(5));
        } else {
            throw new CustomException("Invalid format of line: " + lineOfFile);
        }
    }
}
