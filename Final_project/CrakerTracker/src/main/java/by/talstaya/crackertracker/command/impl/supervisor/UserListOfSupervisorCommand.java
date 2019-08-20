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

/**
 * This class is used to show all users of some supervisor
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class UserListOfSupervisorCommand implements Command {

    private static final int NUMBER_USERS_PER_PAGE = 8;
    private static final String USERS_PER_PAGE = "usersPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String COMMAND_VALUE = "commandValue";
    private static final String USER_LIST_OF_SUPERVISOR_COMMAND = "user_list_of_supervisor";
    private static final String USER_LIST_SIZE = "userListSize";

    private static final String USER = "User";
    private static final String USERS_OF_SUPERVISOR = "usersOfSupervisor";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int indexOfPage;
        if (request.getParameter(INDEX_OF_PAGE) != null) {
            indexOfPage = Integer.parseInt(request.getParameter(INDEX_OF_PAGE));
        } else {
            indexOfPage = 1;
        }

        User supervisor = (User)request.getSession().getAttribute(USER);
        UserService userService = new UserServiceImpl();

        List<User> usersOfSupervisor = userService.findUsersOfSupervisorWithLimit(supervisor.getUserId(),
                (indexOfPage - 1) * NUMBER_USERS_PER_PAGE,
                indexOfPage * NUMBER_USERS_PER_PAGE);

        request.setAttribute(USERS_OF_SUPERVISOR, usersOfSupervisor);

        request.setAttribute(INDEX_OF_PAGE, indexOfPage);
        request.setAttribute(USER_LIST_SIZE, userService.findUsersOfSupervisor(supervisor.getUserId()).size());
        request.setAttribute(COMMAND_VALUE, USER_LIST_OF_SUPERVISOR_COMMAND);
        request.setAttribute(USERS_PER_PAGE, NUMBER_USERS_PER_PAGE);

        return JspPath.USER_LIST_OF_SUPERVISOR.getUrl();

    }
}
