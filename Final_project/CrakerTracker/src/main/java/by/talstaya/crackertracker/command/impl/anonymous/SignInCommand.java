package by.talstaya.crackertracker.command.impl.anonymous;

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
import java.util.Collections;
import java.util.List;

/**
 * This class is used for to sign in
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class SignInCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static final String EMAIL_OR_USERNAME = "emailOrUsername";
    private static final String PASSWORD = "password";
    private static final String USER = "User";
    private static final String ERROR_INPUT_DATA = "errorInputData";
    private static final String RESPONSE = "response";
    private static final String PRODUCT_LIST_PATH = "/visit_product_list";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    private List<UserType> userTypeList;

    public SignInCommand() {
        userTypeList = Collections.singletonList(UserType.ANONYMOUS);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String page;

        UserService userService = new UserServiceImpl();

        String emailOrUsername = request.getParameter(EMAIL_OR_USERNAME);
        String password = request.getParameter(PASSWORD);

        if(emailOrUsername!=null && password!=null){
            User user = null;

            if (emailOrUsername.contains("@")) {
                if (!userService.findByEmailAndPass(emailOrUsername, password).isEmpty()) {
                    user = userService.findByEmailAndPass(emailOrUsername, password).get(0);
                }
            } else {
                if (!userService.findByUsernameAndPass(emailOrUsername, password).isEmpty()) {
                    user = userService.findByUsernameAndPass(emailOrUsername, password).get(0);
                }
            }

            if(user!=null){
                request.getSession().setAttribute(USER, user);
                request.setAttribute(RESPONSE, true);
                page = request.getContextPath() + PRODUCT_LIST_PATH;
            }else{
                request.setAttribute(ERROR_INPUT_DATA, "Incorrect username or email or password");
                request.setAttribute(EMAIL_OR_USERNAME, emailOrUsername);
                page = JspPath.SIGN_IN.getUrl();
            }
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            page = JspPath.ERROR.getUrl();
        }

         return page;

    }

}
