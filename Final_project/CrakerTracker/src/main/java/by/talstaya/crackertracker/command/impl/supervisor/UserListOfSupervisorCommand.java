package by.talstaya.crackertracker.command.impl.supervisor;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.command.Pagination;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * This class is used to show all users of some supervisor
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class UserListOfSupervisorCommand implements Command, Pagination {

    private static final int NUMBER_USERS_PER_PAGE = 8;
    private static final String USERS_PER_PAGE = "usersPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String CHANGE_PAGE = "changePage";
    private static final String START_INDEX_OF_USER_LIST = "startIndexOfUserList";

    private static final String USER = "User";
    private static final String USERS_OF_SUPERVISOR = "usersOfSupervisor";

    private List<UserType> userTypeList;

    public UserListOfSupervisorCommand() {
        userTypeList = Collections.singletonList(UserType.SUPERVISOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        initPaginationParams(request,
                NUMBER_USERS_PER_PAGE,
                USERS_PER_PAGE,
                INDEX_OF_PAGE,
                START_INDEX_OF_USER_LIST,
                CHANGE_PAGE);

        User supervisor = (User)request.getSession().getAttribute(USER);
        UserService userService = new UserServiceImpl();

        List<User> usersOfSupervisor = userService.findUsersOfSupervisor(supervisor.getUserId());


        request.setAttribute(USERS_OF_SUPERVISOR, usersOfSupervisor);

        return JspPath.USER_LIST_OF_SUPERVISOR.getUrl();

    }
}
