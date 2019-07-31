package by.talstaya.crackertracker.service.impl;

import by.talstaya.crackertracker.dao.CommentForUserDao;
import by.talstaya.crackertracker.dao.comment.CommentForUserDaoImpl;
import by.talstaya.crackertracker.entity.CommentForUser;
import by.talstaya.crackertracker.exception.DaoException;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.CommentForUserService;

import java.util.List;

public class CommentForUserServiceImpl implements CommentForUserService {

    private CommentForUserDao commentForUserDao;

    public CommentForUserServiceImpl() {
        commentForUserDao = new CommentForUserDaoImpl();
    }

    @Override
    public List<CommentForUser> findComments(int userId, String mealDate) throws ServiceException {
        try {
            return commentForUserDao.findComments(userId, mealDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void insertComment(CommentForUser commentForUser) throws ServiceException {
        try {
            commentForUserDao.insert(commentForUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteComment(int commentId) throws ServiceException {
        try {
            commentForUserDao.delete(commentId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
