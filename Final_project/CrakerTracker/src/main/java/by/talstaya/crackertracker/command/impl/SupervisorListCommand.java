package by.talstaya.crackertracker.command.impl;

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
 * This class is used to show al  supervisors to user
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class SupervisorListCommand implements Command, Pagination {

    private static final int NUMBER_SUPERVISORS_PER_PAGE = 8;

    private static final String SUPERVISORS_PER_PAGE = "supervisorsPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String CHANGE_PAGE = "changePage";
    private static final String START_INDEX_OF_SUPERVISOR_LIST = "startIndexOfSupervisorList";

    private static final String SUPERVISOR_LIST = "supervisorList";

    private static final String USER = "User";
    private static final String CONTAINS_SUPERVISOR_OR_REQUEST_FOR_SUPERVISOR = "containsSupervisorOrRequestForSupervisor";
    private static final String REQUESTED_SUPERVISOR_ID = "requestedSupervisorId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User) request.getSession().getAttribute(USER);

        initPaginationParams(request,
                NUMBER_SUPERVISORS_PER_PAGE,
                SUPERVISORS_PER_PAGE,
                INDEX_OF_PAGE,
                START_INDEX_OF_SUPERVISOR_LIST,
                CHANGE_PAGE);


        UserService userService = new UserServiceImpl();
        List<User> supervisorList = userService.findAllSupervisors();

        if (userService.containsSupervisorOrRequestForSupervisor(user.getUserId())) {
            request.setAttribute(CONTAINS_SUPERVISOR_OR_REQUEST_FOR_SUPERVISOR, true);
        }

        int requestedSupervisorId = userService.findRequestedSupervisorId(user.getUserId());

        request.setAttribute(SUPERVISOR_LIST, supervisorList);
        request.setAttribute(REQUESTED_SUPERVISOR_ID, requestedSupervisorId);

        return JspPath.SUPERVISOR_LIST.getUrl();

    }
}
