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
 * This class is used to show all requests for some supervisor
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ShowRequestsForSupervisorCommand implements Command {

    private static final String USER = "User";

    private static final int NUMBER_REQUESTS_PER_PAGE = 8;
    private static final String REQUESTS_PER_PAGE = "requestsPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String SHOW_REQUESTS_FOR_SUPERVISOR_COMMAND = "show_requests_for_supervisor";
    private static final String COMMAND_VALUE = "commandValue";
    private static final String REQUEST_LIST_SIZE = "requestListSize";

    private static final String REQUEST_LIST_FOR_SUPERVISOR = "requestListForSupervisor";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int indexOfPage;
        if (request.getParameter(INDEX_OF_PAGE) != null) {
            indexOfPage = Integer.parseInt(request.getParameter(INDEX_OF_PAGE));
        } else {
            indexOfPage = 1;
        }

        User supervisor = (User) request.getSession().getAttribute(USER);

        UserService userService = new UserServiceImpl();
        List<User> requestsForSupervisor = userService.findRequestsForSupervisorWithLimit(supervisor.getUserId(),
                (indexOfPage - 1) * NUMBER_REQUESTS_PER_PAGE,
                indexOfPage * NUMBER_REQUESTS_PER_PAGE);

        request.setAttribute(REQUEST_LIST_FOR_SUPERVISOR, requestsForSupervisor);

        request.setAttribute(INDEX_OF_PAGE, indexOfPage);
        request.setAttribute(REQUEST_LIST_SIZE, userService.findRequestsForSupervisor(supervisor.getUserId()).size());
        request.setAttribute(COMMAND_VALUE, SHOW_REQUESTS_FOR_SUPERVISOR_COMMAND);
        request.setAttribute(REQUESTS_PER_PAGE, NUMBER_REQUESTS_PER_PAGE);

        return JspPath.REQUESTS_FOR_SUPERVISOR.getUrl();
    }
}
