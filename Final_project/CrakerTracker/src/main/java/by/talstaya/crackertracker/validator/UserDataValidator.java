package by.talstaya.crackertracker.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ProductDataValidator is used to validate user parameters
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class UserDataValidator {

    private Map<String, String> errorMessages = new HashMap<>();

    private static final String STRING_REGEX_ALPHABETIC_STRING = "^(\\p{IsAlphabetic}+){1,50}$";
    private static final String STRING_REGEX_USERNAME = "^[\\w][.\\w]{2,48}[\\w]$";
    private static final String STRING_REGEX_POSITIVE_NUMBER = "^[1-9]\\d{1,2}(\\.[\\d])?$";
    private static final String STRING_REGEX_PASSWORD = "(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z]).{7,16}";

    public Map<String, String> validateData(String firstName,
                           String surname,
                           String username,
                           String weight,
                           String height,
                           String password,
                           String confirmedPassword) {

        errorMessages = validateData(firstName, surname, username, weight, height);

        Pattern regexPassword = Pattern.compile(STRING_REGEX_PASSWORD);

        Matcher matcherPassword = regexPassword.matcher(password);
        Matcher matcherConfirmedPassword = regexPassword.matcher(confirmedPassword);

        if (!matcherPassword.matches() || !matcherConfirmedPassword.matches()) {
            errorMessages.put("errorPassword", "Password does not match the requirements");
        }

        return errorMessages;
    }

    public Map<String, String> validateData(String firstName,
                                            String surname,
                                            String username,
                                            String weight,
                                            String height) {

        Pattern regexAlphabeticString = Pattern.compile(STRING_REGEX_ALPHABETIC_STRING);
        Pattern regexPositiveNumber = Pattern.compile(STRING_REGEX_POSITIVE_NUMBER);
        Pattern regexUsername = Pattern.compile(STRING_REGEX_USERNAME);

        Matcher matcherFirstName = regexAlphabeticString.matcher(firstName);
        Matcher matcherSurname = regexAlphabeticString.matcher(surname);
        Matcher matcherUsername = regexUsername.matcher(username);
        Matcher matcherWeight = regexPositiveNumber.matcher(weight);
        Matcher matcherHeight = regexPositiveNumber.matcher(height);

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

        return errorMessages;

    }



}
