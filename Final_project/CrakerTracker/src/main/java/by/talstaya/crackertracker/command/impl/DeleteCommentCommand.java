package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserCommentService;
import by.talstaya.crackertracker.service.impl.UserCommentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to delete comment
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class DeleteCommentCommand implements Command {

    private static final String COMMENT_ID = "commentId";
    private static final String RESPONSE = "response";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int commentId = Integer.parseInt(request.getParameter(COMMENT_ID));

        UserCommentService userCommentService = new UserCommentServiceImpl();
        userCommentService.deleteComment(commentId);

        request.setAttribute(RESPONSE, true);
        return request.getHeader("Referer");
    }
}
