package by.talstaya.crackertracker.command.impl.administrator;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.command.Pagination;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class is used to show all users to administrator
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class UserListCommand implements Command, Pagination {

    private static final int NUMBER_USERS_PER_PAGE = 8;
    private static final String USERS_PER_PAGE = "usersPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String CHANGE_PAGE = "changePage";
    private static final String START_INDEX_OF_USER_LIST = "startIndexOfUserList";

    private static final String USER_LIST = "userList";
    private static final String GREATER_THAN_ONE_ADMIN = "greaterThanOneAdmin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        UserService userService = new UserServiceImpl();

        initPaginationParams(request,
                NUMBER_USERS_PER_PAGE,
                USERS_PER_PAGE,
                INDEX_OF_PAGE,
                START_INDEX_OF_USER_LIST,
                CHANGE_PAGE);

        List<User> userList = userService.takeAllUsers();

        if (userList.stream()
                .filter(user -> user.getUserType().name().equals("ADMINISTRATOR"))
                .count() > 1) {
            request.setAttribute(GREATER_THAN_ONE_ADMIN, true);
        } else {
            request.setAttribute(GREATER_THAN_ONE_ADMIN, false);
        }

        request.setAttribute(USER_LIST, userList);

        return JspPath.USER_LIST.getUrl();
    }
}
