package by.talstaya.crackertracker.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationDataValidator {

    private Map<String, String> errorMessages = new HashMap<>();

    private static final String STRING_REGEX_ALPHABETIC_STRING = "\\p{IsAlphabetic}+";
    private static final String STRING_REGEX_USERNAME = "[A-Za-z0-9_][.A-Za-z0-9_]{2,48}[A-Za-z0-9_]";
    private static final String STRING_REGEX_POSITIVE_NUMBER = "[0-9]+(\\.[0-9]+)?";
    private static final String STRING_REGEX_PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{7,16}";

    public Map<String, String> validateData(String firstName,
                           String surname,
                           String username,
                           String weight,
                           String height,
                           String password,
                           String confirmedPassword) {

        Pattern regexAlphabeticString = Pattern.compile(STRING_REGEX_ALPHABETIC_STRING);
        Pattern regexPositiveNumber = Pattern.compile(STRING_REGEX_POSITIVE_NUMBER);
        Pattern regexPassword = Pattern.compile(STRING_REGEX_PASSWORD);
        Pattern regexUsername = Pattern.compile(STRING_REGEX_USERNAME);

        Matcher matcherFirstName = regexAlphabeticString.matcher(firstName);
        Matcher matcherSurname = regexAlphabeticString.matcher(surname);
        Matcher matcherUsername = regexUsername.matcher(username);
        Matcher matcherWeight = regexPositiveNumber.matcher(weight);
        Matcher matcherHeight = regexPositiveNumber.matcher(height);
        Matcher matcherPassword = regexPassword.matcher(password);
        Matcher matcherConfirmedPassword = regexPassword.matcher(confirmedPassword);

        if (!matcherFirstName.matches()) {
            errorMessages.put("errorFirstName", "First name can only use letters");
        }

        if (!matcherSurname.matches()) {
            errorMessages.put("errorSurname", "Surname can only use letters");
        }

        if (!matcherUsername.matches()) {
            errorMessages.put("errorUsername", "Username does not match the requirements");
        }

        if (!weight.isEmpty() && !matcherWeight.matches()) {
            errorMessages.put("errorWeight", "Weight can use only positive integers or float numbers");
        }

        if (!height.isEmpty() && !matcherHeight.matches()) {
            errorMessages.put("errorHeight", "Height can use only positive integers or float numbers");
        }

        if (!matcherPassword.matches() || !matcherConfirmedPassword.matches()) {
            errorMessages.put("errorPassword", "Password does not match the requirements");
        }

        return errorMessages;
    }
}
