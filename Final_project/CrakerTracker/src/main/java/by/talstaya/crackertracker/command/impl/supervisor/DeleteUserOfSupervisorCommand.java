package by.talstaya.crackertracker.command.impl.supervisor;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserOfSupervisorCommand implements Command {

    private static final String USER = "User";
    private static final String USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User supervisor = (User)request.getSession().getAttribute(USER);
        int userId = Integer.parseInt(request.getParameter(USER_ID));

        UserService userService = new UserServiceImpl();
        userService.deleteUserOfSupervisor(supervisor.getUserId(), userId);

        return new UserListOfSupervisorCommand().execute(request, response);
    }
}
