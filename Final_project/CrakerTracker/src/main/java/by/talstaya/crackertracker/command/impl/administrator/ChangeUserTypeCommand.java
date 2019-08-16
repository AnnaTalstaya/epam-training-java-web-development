package by.talstaya.crackertracker.command.impl.administrator;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * This class is used to change type of user
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ChangeUserTypeCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String USER_TYPE = "userType";
    private static final String RESPONSE = "response";

    private List<UserType> userTypeList;

    public ChangeUserTypeCommand() {
        userTypeList = Collections.singletonList(UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        String userType = request.getParameter(USER_TYPE);

        UserService userService = new UserServiceImpl();

        UserType currentUserType = userService.takeUserType(userId);
        if(!currentUserType.name().equals(userType)) {
            if(currentUserType.equals(UserType.SUPERVISOR)) {
                userService.deleteAllRequestsForSupervisor(userId);
                userService.deleteAllSupervisorIdBySupervisor(userId);
            }

            userService.updateUserType(userId, userType);
        }

        request.setAttribute(RESPONSE, true);
        return request.getHeader("Referer");
    }
}
