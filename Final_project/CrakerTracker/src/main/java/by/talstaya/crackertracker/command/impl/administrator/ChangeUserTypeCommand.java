package by.talstaya.crackertracker.command.impl.administrator;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.impl.ShowUserDetailsCommand;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserTypeCommand implements Command {

    private final String USER_ID = "userId";
    private final String USER_DETAILS = "userDetails";
    private final String USER_TYPE = "userType";
    private final String OK = "ok";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        String userType = request.getParameter(USER_TYPE);

        UserService userService = new UserServiceImpl();
        userService.updateUserType(userId, userType);

        request.setAttribute(OK, true);

        if(Boolean.parseBoolean(request.getParameter(USER_DETAILS))){
            request.setAttribute(USER_ID, userId);
            return new ShowUserDetailsCommand().execute(request, response);
        }else{
            return new UserListCommand().execute(request, response);
        }
    }
}
