package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * This class is used for translation
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class TranslateCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static final String LANGUAGE = "language";
    private static final String LOCALE = "locale";
    private static final String RESPONSE = "response";

    private List<UserType> userTypeList;

    public TranslateCommand() {
        userTypeList = Arrays.asList(UserType.ANONYMOUS, UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String stringLanguage = request.getParameter(LANGUAGE);
        Locale locale;
        switch (stringLanguage) {
            case "Русский":
                locale = new Locale("ru");
                break;
            case "English":
                locale = new Locale("en");
                break;
            case "Français":
                locale = new Locale("fr");
                break;
            default:
                LOGGER.error("There is no " + stringLanguage + " language. English language will be used instead.");
                locale = new Locale("en");
                break;
        }

        request.getSession().setAttribute(LOCALE, locale);
        request.setAttribute(RESPONSE, true);

        return request.getHeader("Referer");
    }
}
