package by.talstaya.crackertracker.command.impl.anonymous;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;
import by.talstaya.crackertracker.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private Map<String, String> errorMessages;

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
    private static final String RESPONSE = "response";
    private static final String PRODUCT_LIST_PATH = "/visit_product_list";

    private List<UserType> userTypeList;

    public RegistrationCommand() {
        userTypeList = Collections.singletonList(UserType.ANONYMOUS);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String page = JspPath.ERROR.getUrl();

        boolean fail = false;

        String firstName = request.getParameter(FIRST_NAME);
        String surname = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);
        String username = request.getParameter(USERNAME);
        String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String weight = request.getParameter(WEIGHT);
        String height = request.getParameter(HEIGHT);
        String password = request.getParameter(PASSWORD);
        String confirmedPassword = request.getParameter(CONFIRMED_PASSWORD);

        UserDataValidator userDataValidator = new UserDataValidator();
        errorMessages = userDataValidator.validateData(firstName, surname, username, weight, height, password, confirmedPassword);

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

                    User.Builder newUser = new User.Builder()
                            .setUserType(UserType.USER)
                            .setFirstName(firstName)
                            .setSurname(surname)
                            .setEmail(email)
                            .setUsername(username)
                            .setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));

                    if (!weight.isEmpty()) {
                        newUser.setWeight(Double.parseDouble(weight));
                    }

                    if (!height.isEmpty()) {
                        newUser.setHeight(Double.parseDouble(height));
                    }

                    if(!dateOfBirth.isEmpty()) {
                        newUser.setDateOfBirth(LocalDate.parse(dateOfBirth));
                    }

                    userService.insertUser(newUser.build());

                    User user = userService.findByUsernameAndPass(username, password).get(0);

                    request.getSession().setAttribute(USER, user);

                    request.setAttribute(RESPONSE, true);
                    page = request.getContextPath() + PRODUCT_LIST_PATH;

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

        return page;
    }


}
