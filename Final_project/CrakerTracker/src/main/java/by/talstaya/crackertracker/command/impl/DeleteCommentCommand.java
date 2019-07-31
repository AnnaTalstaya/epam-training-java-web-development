package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.CommentForUserService;
import by.talstaya.crackertracker.service.impl.CommentForUserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommentCommand implements Command {

    private static final String COMMENT_ID = "commentId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int commentId = Integer.parseInt(request.getParameter(COMMENT_ID));

        CommentForUserService commentForUserService = new CommentForUserServiceImpl();
        commentForUserService.deleteComment(commentId);

        return new ShowDietCommand().execute(request, response);
    }
}
