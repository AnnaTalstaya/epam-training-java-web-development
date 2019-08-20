package by.talstaya.crackertracker.command.impl;

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
 * This class is used to show al  supervisors to user
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class SupervisorListCommand implements Command {

    private static final int NUMBER_SUPERVISORS_PER_PAGE = 8;

    private static final String SUPERVISORS_PER_PAGE = "supervisorsPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String SUPERVISOR_LIST_COMMAND = "supervisor_list";
    private static final String COMMAND_VALUE = "commandValue";
    private static final String SUPERVISOR_LIST_SIZE = "supervisorListSize";

    private static final String SUPERVISOR_LIST = "supervisorList";

    private static final String USER = "User";
    private static final String CONTAINS_SUPERVISOR_OR_REQUEST_FOR_SUPERVISOR = "containsSupervisorOrRequestForSupervisor";
    private static final String REQUESTED_SUPERVISOR_ID = "requestedSupervisorId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int indexOfPage;
        if (request.getParameter(INDEX_OF_PAGE) != null) {
            indexOfPage = Integer.parseInt(request.getParameter(INDEX_OF_PAGE));
        } else {
            indexOfPage = 1;
        }

        User user = (User) request.getSession().getAttribute(USER);

        UserService userService = new UserServiceImpl();
        List<User> supervisorList = userService.findAllSupervisorsWithLimit(
                (indexOfPage - 1) * NUMBER_SUPERVISORS_PER_PAGE,
                indexOfPage * NUMBER_SUPERVISORS_PER_PAGE);

        if (userService.containsSupervisorOrRequestForSupervisor(user.getUserId())) {
            request.setAttribute(CONTAINS_SUPERVISOR_OR_REQUEST_FOR_SUPERVISOR, true);
        }

        int requestedSupervisorId = userService.findRequestedSupervisorId(user.getUserId());

        request.setAttribute(SUPERVISOR_LIST, supervisorList);
        request.setAttribute(REQUESTED_SUPERVISOR_ID, requestedSupervisorId);

        request.setAttribute(INDEX_OF_PAGE, indexOfPage);
        request.setAttribute(SUPERVISOR_LIST_SIZE, userService.findAllSupervisors().size());
        request.setAttribute(COMMAND_VALUE, SUPERVISOR_LIST_COMMAND);
        request.setAttribute(SUPERVISORS_PER_PAGE, NUMBER_SUPERVISORS_PER_PAGE);

        return JspPath.SUPERVISOR_LIST.getUrl();

    }
}
