package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used for to visit page of settings with default params
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class VisitSettingsCommand implements Command {

    private List<UserType> userTypeList;

    public VisitSettingsCommand() {
        userTypeList = Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return JspPath.SETTINGS.getUrl();
    }

}
