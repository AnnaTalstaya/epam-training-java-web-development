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

public class DeleteUserCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String RESPONSE = "response";
    private static final String USER_LIST_PATH = "/user_list";

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

        request.setAttribute(RESPONSE, true);
        return request.getContextPath() + USER_LIST_PATH;
    }
}
