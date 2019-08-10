package by.talstaya.crackertracker.command.impl.anonymous;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class VisitRegistrationCommand implements Command {

    private static final String ERROR = "error";

    private List<UserType> userTypeList;

    public VisitRegistrationCommand() {
        userTypeList = Collections.singletonList(UserType.ANONYMOUS);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return JspPath.REGISTRATION.getUrl();
    }
}
