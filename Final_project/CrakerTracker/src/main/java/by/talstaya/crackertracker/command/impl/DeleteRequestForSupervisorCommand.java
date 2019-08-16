package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to delete request send to supervisor by user
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class DeleteRequestForSupervisorCommand implements Command {

    private static final String USER = "User";
    private static final String RESPONSE = "response";

    private List<UserType> userTypeList;

    public DeleteRequestForSupervisorCommand() {
        userTypeList = Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User)request.getSession().getAttribute(USER);
        UserService userService = new UserServiceImpl();
        userService.deleteRequestForSupervisor(user.getUserId());

        request.setAttribute(RESPONSE, true);
        return request.getHeader("Referer");
    }
}
