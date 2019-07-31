package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.DaoException;

import java.util.List;

public abstract class UserDao extends BasicDao<User> {

    public abstract List<User> takeAllUsers() throws DaoException;

    public abstract List<User> findByUsernameAndPass(String username, String password) throws DaoException;

    public abstract List<User> findByEmailAndPass(String email, String password) throws DaoException;

    public abstract List<User> findAllSupervisors() throws DaoException;

    public abstract List<User> findUsersOfSupervisor(int supervisorId) throws DaoException;

    public abstract List<User> findRequestsForSupervisor(int supervisorId) throws DaoException;

    public abstract int findRequestedSupervisorId(int userId) throws DaoException;

    public abstract int findSupervisorId(int userId) throws DaoException;

    public abstract void updateUserType(int userId, String userType) throws DaoException;

    public abstract void updateRating(int supervisorId, double averageRating) throws DaoException;

    public abstract boolean containsEmail(String email) throws DaoException;

    public abstract boolean containsUsername(String username) throws DaoException;

    public abstract void putRequestForSupervisor(int userId, int supervisorId) throws DaoException;

    public abstract void updateSupervisorId(int supervisorId, int userId) throws DaoException;

    public abstract boolean containsSupervisor(int userId) throws DaoException;

    public abstract boolean containsRequestForSupervisor(int userId) throws DaoException;

    public abstract void deleteRequestForSupervisor(int userId) throws DaoException;

}
