package by.talstaya.crackertracker.command.impl;

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

public class ShowUserDetailsCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String ERROR = "error";
    private static final String USER = "user";
    private static final String GREATER_THAN_ONE_ADMIN = "greaterThanOneAdmin";

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

        int userId = Integer.parseInt(request.getParameter(USER_ID));
        boolean greaterThanOneAdmin = Boolean.parseBoolean(request.getParameter(GREATER_THAN_ONE_ADMIN));

        if(userId != 0){

            UserService userService = new UserServiceImpl();
            User user = userService.findUserById(userId);

            if(user == null){
                request.setAttribute(ERROR, "User with such id not found");
                return JspPath.ERROR.getUrl();
            } else{
                request.setAttribute(USER, user);
                request.setAttribute(GREATER_THAN_ONE_ADMIN, greaterThanOneAdmin);
            }
        } else {
            request.setAttribute(ERROR, "User not found");
            return JspPath.ERROR.getUrl();
        }
        return JspPath.USER.getUrl();
    }
}
