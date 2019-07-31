package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteRequestForSupervisorCommand implements Command {

    private static final String USER = "User";
    private static final String MESSAGE = "message";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User)request.getSession().getAttribute(USER);
        UserService userService = new UserServiceImpl();
        userService.deleteRequestForSupervisor(user.getUserId());

        request.setAttribute(MESSAGE, "Request is deleted");

        return new SupervisorListCommand().execute(request, response);
    }
}
