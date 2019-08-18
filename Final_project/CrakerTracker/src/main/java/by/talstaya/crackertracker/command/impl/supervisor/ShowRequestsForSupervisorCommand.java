package by.talstaya.crackertracker.command.impl.supervisor;

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
 * This class is used to show all requests for some supervisor
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ShowRequestsForSupervisorCommand implements Command, Pagination {

    private static final String USER = "User";

    private static final int NUMBER_REQUESTS_PER_PAGE = 8;
    private static final String REQUESTS_PER_PAGE = "requestsPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String CHANGE_PAGE = "changePage";
    private static final String START_INDEX_OF_REQUEST_LIST = "startIndexOfRequestList";

    private static final String REQUEST_LIST_FOR_SUPERVISOR = "requestListForSupervisor";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        initPaginationParams(request,
                NUMBER_REQUESTS_PER_PAGE,
                REQUESTS_PER_PAGE,
                INDEX_OF_PAGE,
                START_INDEX_OF_REQUEST_LIST,
                CHANGE_PAGE);

        User supervisor = (User) request.getSession().getAttribute(USER);

        UserService userService = new UserServiceImpl();
        List<User> requestsForSupervisor = userService.findRequestsForSupervisor(supervisor.getUserId());

        request.setAttribute(REQUEST_LIST_FOR_SUPERVISOR, requestsForSupervisor);

        return JspPath.REQUESTS_FOR_SUPERVISOR.getUrl();
    }
}
