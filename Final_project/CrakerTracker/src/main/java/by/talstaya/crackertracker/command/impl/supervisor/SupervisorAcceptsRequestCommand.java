package by.talstaya.crackertracker.command.impl.supervisor;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to accept request of user by supervisor
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class SupervisorAcceptsRequestCommand implements Command {

    private static final String USER = "User";
    private static final String USER_ID = "userId";
    private static final String RESPONSE = "response";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User supervisor = (User)request.getSession().getAttribute(USER);
        int userId = Integer.parseInt(request.getParameter(USER_ID));

        UserService userService = new UserServiceImpl();
        userService.supervisorAcceptsRequestFromUser(supervisor.getUserId(), userId);

        request.setAttribute(RESPONSE, true);
        return request.getHeader("Referer");
    }
}
