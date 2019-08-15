package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger("name");

    private Map<String, String> errorMessages;

    private static final String STRING_REGEX_ALPHABETIC_STRING = "\\p{IsAlphabetic}+";
    private static final String STRING_REGEX_USERNAME = "[A-Za-z0-9_][.A-Za-z0-9_]{2,48}[A-Za-z0-9_]";
    private static final String STRING_REGEX_POSITIVE_NUMBER = "[0-9]+(\\.[0-9]+)?";
    private static final String STRING_REGEX_PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{7,16}";

    private static final String USER = "User";
    private static final String FIRST_NAME = "firstName";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String WEIGHT = "weight";
    private static final String HEIGHT = "height";
    private static final String CURRENT_PASSWORD = "currentPassword";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String CONFIRMED_PASSWORD = "confirmedNewPassword";
    private static final String ERROR_MAIL = "errorEmail";
    private static final String ERROR_USERNAME = "errorUsername";
    private static final String ERROR_PASSWORD = "errorPassword";
    private static final String ERROR_PASS_AND_CONFIRMED_PASS = "errorPassAndConfirmedPassMessage";
    private static final String ERROR_DATA = "errorData";
    private static final String OK = "ok";

    private List<UserType> userTypeList;

    public SettingsCommand() {
        userTypeList = Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        boolean fail = false;
        errorMessages = new HashMap<>();

        User user = (User) request.getSession().getAttribute(USER);
        UserService userService = new UserServiceImpl();

        String firstName = request.getParameter(FIRST_NAME);
        String surname = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);
        String username = request.getParameter(USERNAME);
        String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String weight = request.getParameter(WEIGHT);
        String height = request.getParameter(HEIGHT);

        String currentPassword = request.getParameter(CURRENT_PASSWORD);
        String newPassword = request.getParameter(NEW_PASSWORD);
        String confirmedNewPassword = request.getParameter(CONFIRMED_PASSWORD);

        validateValues(firstName, surname, username, weight, height);

        boolean updatePass = false;

        if (!user.getEmail().equals(email) && userService.containsEmail(email)) {
            errorMessages.put(ERROR_MAIL, "This email is taken by another account");
        }
        if (!user.getUsername().equals(username) && !errorMessages.containsKey("errorUsername")
                && userService.containsUsername(username)) {
            errorMessages.put(ERROR_USERNAME, "This username is taken by another account");
        }

        if (!newPassword.isEmpty()) {
            if (!confirmedNewPassword.isEmpty()) {
                if (currentPassword.equals(user.getPassword())) {
                    if (!validatePass(newPassword) || !validatePass(confirmedNewPassword)) {
                        errorMessages.put(ERROR_PASSWORD, "Password does not match the requirements");
                    } else {
                        if (Objects.equals(newPassword, confirmedNewPassword)) {
                            updatePass = true;
                        } else {
                            errorMessages.put(ERROR_PASS_AND_CONFIRMED_PASS, "Password and confirmed password do not match");
                        }
                    }
                } else {
                    errorMessages.put(ERROR_PASSWORD, "Wrong password");
                }

            } else {
                errorMessages.put(ERROR_PASS_AND_CONFIRMED_PASS, "Password and confirmed password do not match");
            }
        }

        if (errorMessages.isEmpty()) {
            User.Builder userBuilder = new User.Builder()
                    .setFirstName(firstName)
                    .setSurname(surname)
                    .setEmail(email)
                    .setUsername(username)
                    .setWeight(Double.parseDouble(weight))
                    .setHeight(Double.parseDouble(height));

            if (updatePass) {
                userBuilder.setPassword(newPassword);
            }

            if(!dateOfBirth.isEmpty()) {
                userBuilder.setDateOfBirth(LocalDate.parse(dateOfBirth));
            }

            user = userBuilder.build();
        } else {
            fail = true;
        }

        request.setAttribute(FIRST_NAME, firstName);
        request.setAttribute(SURNAME, surname);
        request.setAttribute(EMAIL, email);
        request.setAttribute(USERNAME, username);
        request.setAttribute(DATE_OF_BIRTH, dateOfBirth);
        request.setAttribute(WEIGHT, weight);
        request.setAttribute(HEIGHT, height);

        if (fail) {
            errorMessages.forEach(request::setAttribute);
            request.setAttribute(ERROR_DATA, "Changes not saved");
        } else {
            request.setAttribute(CURRENT_PASSWORD, currentPassword);
            request.setAttribute(NEW_PASSWORD, newPassword);
            request.setAttribute(CONFIRMED_PASSWORD, confirmedNewPassword);

            request.setAttribute(OK, "Changes saved");
        }

        return JspPath.SETTINGS.getUrl();
    }

    private boolean validatePass(String password) {
        Pattern regexPassword = Pattern.compile(STRING_REGEX_PASSWORD);
        Matcher matcherPassword = regexPassword.matcher(password);

        if (!matcherPassword.matches()) {
            errorMessages.put("errorPassword", "Password does not match the requirements");
            return false;
        }
        return true;
    }

    private void validateValues(String firstName,
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

    }
}
