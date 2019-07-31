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

public class ShowRequestsForSupervisorCommand implements Command {

    private final String USER = "User";

    private final int NUMBER_REQUESTS_PER_PAGE = 8;
    private final String REQUESTS_PER_PAGE = "requestsPerPage";
    private final String INDEX_OF_PAGE = "indexOfPage";
    private final String CHANGE_PAGE = "changePage";
    private final String START_INDEX_OF_REQUEST_LIST = "startIndexOfRequestList";

    private final String REQUEST_LIST_FOR_SUPERVISOR = "requestListForSupervisor";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int requestsPerPage;
        if (request.getParameter(REQUESTS_PER_PAGE) == null) {  //if we open the jsp the first time
            requestsPerPage = NUMBER_REQUESTS_PER_PAGE;
        } else {
            requestsPerPage = Integer.parseInt(request.getParameter(REQUESTS_PER_PAGE));
        }

        int newIndex;
        if (request.getParameter(INDEX_OF_PAGE) == null) {
            newIndex = 1;
        } else {
            newIndex = (int) Double.parseDouble(request.getParameter(INDEX_OF_PAGE)) + Integer.parseInt(request.getParameter(CHANGE_PAGE));
        }

        User supervisor = (User)request.getSession().getAttribute(USER);

        UserService userService = new UserServiceImpl();
        List<User> requestsForSupervisor = userService.findRequestsForSupervisor(supervisor.getUserId());

        request.setAttribute(REQUESTS_PER_PAGE, requestsPerPage);
        request.setAttribute(START_INDEX_OF_REQUEST_LIST, (newIndex - 1) * requestsPerPage);
        request.setAttribute(INDEX_OF_PAGE, newIndex);

        request.setAttribute(REQUEST_LIST_FOR_SUPERVISOR, requestsForSupervisor);

        return JspPath.REQUESTS_FOR_SUPERVISORS.getUrl();
    }
}
