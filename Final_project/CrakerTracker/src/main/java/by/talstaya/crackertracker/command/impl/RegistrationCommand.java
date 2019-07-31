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
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private Map<String, String> errorMessages;

    private static final String PAGE_IS_ACTIVATED = "page_is_activated";

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
    private static final String PASSWORD = "password";
    private static final String CONFIRMED_PASSWORD = "confirmedPassword";
    private static final String ERROR_EMAIL = "errorEmail";
    private static final String ERROR_USERNAME = "errorUsername";
    private static final String ERROR_PASS_AND_CONFIRMED_PASS = "errorPassAndConfirmedPassMessage";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String page = JspPath.ERROR.getUrl();

        if(request.getParameter(PAGE_IS_ACTIVATED) == null){
            page = JspPath.REGISTRATION.getUrl();
        } else {
            boolean fail = false;
            errorMessages = new HashMap<>();

            String firstName = request.getParameter(FIRST_NAME);
            String surname = request.getParameter(SURNAME);
            String email = request.getParameter(EMAIL);
            String username = request.getParameter(USERNAME);
            String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
            String weight = request.getParameter(WEIGHT);
            String height = request.getParameter(HEIGHT);
            String password = request.getParameter(PASSWORD);
            String confirmedPassword = request.getParameter(CONFIRMED_PASSWORD);

            validateValues(firstName, surname, username, weight, height, password, confirmedPassword);

            if (errorMessages.isEmpty()) {
                if (Objects.equals(password, confirmedPassword)) {
                    UserService userService = new UserServiceImpl();

                    if (userService.containsEmail(email)) {
                        errorMessages.put(ERROR_EMAIL, "This email is taken by another account");
                    }
                    if (userService.containsUsername(username)) {
                        errorMessages.put(ERROR_USERNAME, "This username is taken by another account");
                    }

                    if (errorMessages.isEmpty()) {

                        double weightDouble, heightDouble;
                        if (!weight.isEmpty()) {
                            weightDouble = Double.parseDouble(weight);
                        } else {
                            weightDouble = 0;
                        }

                        if (!height.isEmpty()) {
                            heightDouble = Double.parseDouble(height);
                        } else {
                            heightDouble = 0;
                        }

                        userService.insertUser(new User.Builder()
                                .setUserType(UserType.USER)
                                .setFirstName(firstName)
                                .setSurname(surname)
                                .setEmail(email)
                                .setUsername(username)
                                .setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)))
                                .setDateOfBirth(dateOfBirth)
                                .setWeight(weightDouble)
                                .setHeight(heightDouble)
                                .build());

                        User user = userService.findByUsernameAndPass(username, password).get(0);

                        request.getSession().setAttribute(USER, user);

                        page = JspPath.SETTINGS.getUrl();

                    } else {
                        fail = true;
                        page = JspPath.REGISTRATION.getUrl();
                    }
                } else {
                    fail = true;
                    errorMessages.put(ERROR_PASS_AND_CONFIRMED_PASS, "Password and confirmed password do not match");
                }
            } else {
                fail = true;
            }

            if (fail) {
                request.setAttribute(FIRST_NAME, firstName);
                request.setAttribute(SURNAME, surname);
                request.setAttribute(EMAIL, email);
                request.setAttribute(USERNAME, username);
                request.setAttribute(DATE_OF_BIRTH, dateOfBirth);
                request.setAttribute(WEIGHT, weight);
                request.setAttribute(HEIGHT, height);

                errorMessages.forEach(request::setAttribute);

                page = JspPath.REGISTRATION.getUrl();
            }
        }

        return page;
    }

    private void validateValues(String firstName,
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
    }

}
