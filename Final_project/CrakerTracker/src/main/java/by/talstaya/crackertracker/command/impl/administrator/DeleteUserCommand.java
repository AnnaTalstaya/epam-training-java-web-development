package by.talstaya.crackertracker.command.impl.administrator;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.impl.ShowUserDetailsCommand;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class DeleteUserCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String USER_DETAILS = "userDetails";

    private List<UserType> userTypeList;

    public DeleteUserCommand() {
        userTypeList = Collections.singletonList(UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int userId = Integer.parseInt(request.getParameter(USER_ID));

        UserService userService = new UserServiceImpl();
        userService.deleteUser(userId);

        if(Boolean.parseBoolean(request.getParameter(USER_DETAILS))){
            request.setAttribute(USER_ID, userId);
            return new ShowUserDetailsCommand().execute(request, response);
        }else{
            return new UserListCommand().execute(request, response);
        }
    }
}
