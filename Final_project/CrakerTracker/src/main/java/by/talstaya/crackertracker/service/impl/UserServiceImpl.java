package by.talstaya.crackertracker.service.impl;

import by.talstaya.crackertracker.dao.UserDao;
import by.talstaya.crackertracker.dao.user.UserDaoImpl;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.DaoException;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public List<User> findByUsernameAndPass(String username, String password) throws ServiceException {
        try {
            return userDao.findByUsernameAndPass(username, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findByEmailAndPass(String email, String password) throws ServiceException {
        try {
            return userDao.findByEmailAndPass(email, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllSupervisors() throws ServiceException {
        try {
            return userDao.findAllSupervisors();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUsersOfSupervisor(int supervisorId) throws ServiceException {
        try {
            return userDao.findUsersOfSupervisor(supervisorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserById(int userId) throws ServiceException {
        try {
            return userDao.findById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findRequestedSupervisorId(int userId) throws ServiceException {
        try {
            return userDao.findRequestedSupervisorId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findSupervisorOfUser(int userId) throws ServiceException {
        try {
            int supervisorId = userDao.findSupervisorId(userId);
            return userDao.findById(supervisorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void insertUser(User user) throws ServiceException {
        try {
            userDao.insert(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(int userId) throws ServiceException {
        try {
            userDao.delete(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean containsEmail(String email) throws ServiceException {
        try {
            return userDao.containsEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean containsUsername(String username) throws ServiceException {
        try {
            return userDao.containsUsername(username);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserType(int userId, String userType) throws ServiceException {
        try {
            userDao.updateUserType(userId, userType);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void putRequestForSupervisor(int userId, int supervisorId) throws ServiceException {
        try {
            userDao.putRequestForSupervisor(userId, supervisorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean containsSupervisorOrRequestForSupervisor(int userId) throws ServiceException {
        try {
            return userDao.containsSupervisor(userId) || userDao.containsRequestForSupervisor(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteRequestForSupervisor(int userId) throws ServiceException {
        try {
            userDao.deleteRequestForSupervisor(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUserOfSupervisor(int supervisorId, int userId) throws ServiceException {
        try {
            userDao.deleteUserOfSupervisor(supervisorId, userId);  //here transaction is used
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteSupervisorOfUser(int userId) throws ServiceException {
        try {
            userDao.updateSupervisorId(0, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> takeAllUsers() throws ServiceException {
        try {
            return userDao.takeAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findRequestsForSupervisor(int supervisorId) throws ServiceException {
        try {
            return userDao.findRequestsForSupervisor(supervisorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void supervisorAcceptsRequestFromUser(int supervisorId, int userId) throws ServiceException {
        try {
            userDao.supervisorAcceptsRequestFromUser(supervisorId, userId); //here transaction is used
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void supervisorRejectsRequestFromUser(int supervisorId, int userId) throws ServiceException {
        try {
            userDao.putRequestForSupervisor(userId, 0);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateRating(int supervisorId, double averageRating) throws ServiceException {
        try {
            userDao.updateRating(supervisorId, averageRating);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserType takeUserType(int userId) throws ServiceException {
        try {
            return userDao.takeUserType(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAllRequestsForSupervisor(int supervisorId) throws ServiceException {
        try {
            userDao.deleteAllRequestsForSupervisor(supervisorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAllSupervisorIdBySupervisor(int supervisorId) throws ServiceException {
        try {
            userDao.deleteAllSupervisorIdBySupervisor(supervisorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
