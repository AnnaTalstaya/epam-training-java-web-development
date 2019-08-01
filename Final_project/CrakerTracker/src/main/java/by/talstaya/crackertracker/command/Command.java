package by.talstaya.crackertracker.command;

import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface Command {

    List<UserType> getUserTypeList();

    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
