package by.talstaya.task01.creator;

import by.talstaya.task01.entity.Developer;
import by.talstaya.task01.entity.Employee;
import by.talstaya.task01.exception.CustomException;
import by.talstaya.task01.validator.LineValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DeveloperCreator implements EmployeeCreator {

    @Override
    public Employee factoryMethod(List<String> lineOfFile) throws CustomException {
        LineValidator validator = new LineValidator();

        if (validator.validLine(lineOfFile)) {
            return new Developer(lineOfFile.get(1),
                    lineOfFile.get(2),
                    new BigDecimal(lineOfFile.get(3)),
                    LocalDate.parse(lineOfFile.get(4), DateTimeFormatter.ofPattern("d/MM/yyyy")),
                    Developer.DeveloperStatus.valueOf(lineOfFile.get(5)));
        } else {
            throw new CustomException("Invalid format of line: " + lineOfFile);
        }
    }
}
