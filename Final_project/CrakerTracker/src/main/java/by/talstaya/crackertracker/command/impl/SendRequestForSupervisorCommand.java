package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendRequestForSupervisorCommand implements Command {

    private final String USER = "User";
    private final String SUPERVISOR_ID = "supervisorId";
    private final String MESSAGE = "message";
    private final String REQUESTED_SUPERVISOR_ID = "requestedSupervisorId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User)request.getSession().getAttribute(USER);
        int supervisorId = Integer.parseInt(request.getParameter(SUPERVISOR_ID));

        UserService userService = new UserServiceImpl();
        userService.putRequestForSupervisor(user.getUserId(), supervisorId);

        request.setAttribute(MESSAGE, "Request is sent");
        request.setAttribute(REQUESTED_SUPERVISOR_ID, supervisorId);

        return new SupervisorListCommand().execute(request, response);
    }
}
