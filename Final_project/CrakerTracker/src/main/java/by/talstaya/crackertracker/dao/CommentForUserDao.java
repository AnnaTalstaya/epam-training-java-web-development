package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.CommentForUser;
import by.talstaya.crackertracker.exception.DaoException;

import java.util.List;

public abstract class CommentForUserDao extends BasicDao<CommentForUser> {

    public abstract List<CommentForUser> findComments(int userId, String mealDate) throws DaoException;
}
