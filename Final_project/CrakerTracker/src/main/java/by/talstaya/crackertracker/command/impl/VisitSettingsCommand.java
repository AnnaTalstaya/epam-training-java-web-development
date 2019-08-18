package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used for to visit page of settings with default params
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class VisitSettingsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return JspPath.SETTINGS.getUrl();
    }

}
