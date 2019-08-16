package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.CommentForUser;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.CommentForUserService;
import by.talstaya.crackertracker.service.impl.CommentForUserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to comment user diet
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class CommentCommand implements Command {

    private static final String USER = "User";
    private static final String MEAL_DATE = "mealDate";
    private static final String USER_ID_FOR_SUPERVISOR = "userIdForSupervisor";
    private static final String COMMENT = "comment";
    private static final String RESPONSE = "response";

    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    private static final String REGEX_COMMENT = "^.{1,2000}$";

    private List<UserType> userTypeList;

    public CommentCommand() {
        userTypeList = Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User) request.getSession().getAttribute(USER);
        String selectedDate = request.getParameter(MEAL_DATE);
        String comment = request.getParameter(COMMENT);

        Pattern pattern = Pattern.compile(REGEX_COMMENT);
        Matcher matcher = pattern.matcher(comment);

        if(matcher.matches()) {
            int userId;
            if (!request.getParameter(USER_ID_FOR_SUPERVISOR).isEmpty()) {
                userId = Integer.parseInt(request.getParameter(USER_ID_FOR_SUPERVISOR));
            } else {
                userId = user.getUserId();
            }

            CommentForUserService commentForUserService = new CommentForUserServiceImpl();
            commentForUserService.insertComment(new CommentForUser.Builder()
                    .setMealDate(LocalDate.parse(selectedDate))
                    .setUserId(userId)
                    .setCommentator(new User.Builder().setUserId(user.getUserId()).build())
                    .setComment(comment.trim())
                    .build());

            request.setAttribute(RESPONSE, true);
            return request.getHeader("Referer");
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return JspPath.ERROR.getUrl();
        }
    }
}
