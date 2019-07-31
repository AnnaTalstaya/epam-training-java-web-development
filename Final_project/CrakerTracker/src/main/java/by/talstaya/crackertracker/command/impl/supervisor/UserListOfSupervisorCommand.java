package by.talstaya.crackertracker.command.impl.supervisor;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListOfSupervisorCommand implements Command {

    private final int NUMBER_USERS_PER_PAGE = 8;
    private final String USERS_PER_PAGE = "usersPerPage";
    private final String INDEX_OF_PAGE = "indexOfPage";
    private final String CHANGE_PAGE = "changePage";
    private final String START_INDEX_OF_USER_LIST = "startIndexOfUserList";

    private final String USER = "User";
    private final String USERS_OF_SUPERVISOR = "usersOfSupervisor";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int usersPerPage;
        if (request.getParameter(USERS_PER_PAGE) == null) {  //if we open the jsp the first time
            usersPerPage = NUMBER_USERS_PER_PAGE;
        } else {
            usersPerPage = Integer.parseInt(request.getParameter(USERS_PER_PAGE));
        }

        int newIndex;
        if (request.getParameter(INDEX_OF_PAGE) == null) {
            newIndex = 1;
        } else {
            newIndex = (int) Double.parseDouble(request.getParameter(INDEX_OF_PAGE)) + Integer.parseInt(request.getParameter(CHANGE_PAGE));
        }

        User supervisor = (User)request.getSession().getAttribute(USER);
        UserService userService = new UserServiceImpl();

        List<User> usersOfSupervisor = userService.findUsersOfSupervisor(supervisor.getUserId());

        request.setAttribute(USERS_PER_PAGE, usersPerPage);
        request.setAttribute(START_INDEX_OF_USER_LIST, (newIndex - 1) * usersPerPage);
        request.setAttribute(INDEX_OF_PAGE, newIndex);

        request.setAttribute(USERS_OF_SUPERVISOR, usersOfSupervisor);

        return JspPath.USER_LIST_OF_SUPERVISOR.getUrl();

    }
}
