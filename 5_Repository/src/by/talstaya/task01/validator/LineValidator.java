package by.talstaya.task01.validator;

import by.talstaya.task01.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineValidator {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private final String STRING_REGEX_DATE = "^((0?[1-9])|([12]\\d)|(3[01]))[/](0?[1-9]|1[12])[/]((000[1-9])|(00[1-9]{2})|(0[1-9]{3})|([1-9]\\d{3}))$";
    private final String STRING_REGEX_SALARY = "^\\d+(\\.\\d+)?$";
    private final String STRING_REGEX_DEVELOPER_STATUS = "^INTERN|JUNIOR|MIDDLE|SENIOR|LEAD|CHIEF$";
    private int numberParametersInLine = 6;

    public boolean validLine(final List<String> parsedString) {
        Pattern regexDate = Pattern.compile(STRING_REGEX_DATE);
        Pattern regexSalary = Pattern.compile(STRING_REGEX_SALARY);
        Pattern regexDeveloperStatus = Pattern.compile(STRING_REGEX_DEVELOPER_STATUS);

        if (parsedString.size() == numberParametersInLine) {
            Matcher matcher;
            String element;

            switch (parsedString.get(0)) {
                case "Developer":
                    element = parsedString.get(5);
                    matcher = regexDeveloperStatus.matcher(element);
                    if (!matcher.matches()) {
                        LOGGER.log(Level.ERROR, "Invalid developer status: " + element);
                        return false;
                    }
                case "Manager":
                    element = parsedString.get(3);
                    matcher = regexSalary.matcher(element);
                    if (!matcher.matches()) {
                        LOGGER.log(Level.ERROR, "Invalid salary: " + element);
                        return false;
                    }

                    element = parsedString.get(4);
                    matcher = regexDate.matcher(element);
                    if (!matcher.matches()) {
                        LOGGER.log(Level.ERROR, "Invalid date: " + element);
                        return false;
                    }
                    break;
                default:
                    LOGGER.log(Level.ERROR, "Invalid type of person: "
                        + parsedString.get(0));
                    return false;
            }
        } else {
            LOGGER.log(Level.ERROR, "Invalid number of parameters: "
                    + parsedString.size());
            return false;
        }

        return true;
    }

}
