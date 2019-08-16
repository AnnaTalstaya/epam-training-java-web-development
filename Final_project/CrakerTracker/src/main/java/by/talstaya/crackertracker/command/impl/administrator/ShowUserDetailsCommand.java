package by.talstaya.crackertracker.command.impl.administrator;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to show user details to administrator
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ShowUserDetailsCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String USER = "user";
    private static final String GREATER_THAN_ONE_ADMIN = "greaterThanOneAdmin";

    private static final String REGEX_ID = "^[1-9]\\d*$";

    private List<UserType> userTypeList;

    public ShowUserDetailsCommand() {
        userTypeList = Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        if(request.getParameter(USER_ID) != null) {

            Pattern pattern = Pattern.compile(REGEX_ID);
            Matcher matcher = pattern.matcher(request.getParameter(USER_ID));

            if(matcher.matches()) {
                int userId = Integer.parseInt(request.getParameter(USER_ID));

                UserService userService = new UserServiceImpl();
                User currentUser = userService.findUserById(userId);

                if(currentUser == null){
                    request.setAttribute(ERROR, "User with such id not found");
                    request.setAttribute(STATUS_CODE, 404);
                    return JspPath.ERROR.getUrl();
                } else{
                    request.setAttribute(USER, currentUser);
                    List<User> userList = userService.takeAllUsers();

                    if (userList.stream()
                            .filter(user -> user.getUserType().name().equals("ADMINISTRATOR"))
                            .count() > 1) {
                        request.setAttribute(GREATER_THAN_ONE_ADMIN, true);
                    } else {
                        request.setAttribute(GREATER_THAN_ONE_ADMIN, false);
                    }
                }
                return JspPath.USER.getUrl();
            } else {
                request.setAttribute(ERROR, "Error request");
                request.setAttribute(STATUS_CODE, 404);
                return JspPath.ERROR.getUrl();
            }
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return JspPath.ERROR.getUrl();
        }

    }
}
