package by.talstaya.crackertracker.service.impl;

import by.talstaya.crackertracker.dao.UserCommentDao;
import by.talstaya.crackertracker.dao.comment.UserCommentDaoImpl;
import by.talstaya.crackertracker.entity.UserComment;
import by.talstaya.crackertracker.exception.DaoException;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserCommentService;

import java.time.LocalDate;
import java.util.List;

/**
 * This class is an implementation of UserCommentService
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class UserCommentServiceImpl implements UserCommentService {

    private UserCommentDao userCommentDao;

    public UserCommentServiceImpl() {
        userCommentDao = new UserCommentDaoImpl();
    }

    @Override
    public List<UserComment> findComments(int userId, String mealDate) throws ServiceException {
        try {
            return userCommentDao.findComments(userId, mealDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void insertComment(UserComment userComment) throws ServiceException {
        try {
            userCommentDao.insert(userComment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteComment(int commentId) throws ServiceException {
        try {
            userCommentDao.delete(commentId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCommentsForUser(int userId) throws ServiceException {
        try {
            userCommentDao.deleteCommentsForUser(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCommentsByCommentator(int commentatorId) throws ServiceException {
        try {
            userCommentDao.deleteCommentsByCommentator(commentatorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws ServiceException {
        try {
            userCommentDao.deleteCommentsForUserByDate(userId, selectedDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
