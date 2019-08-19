package serviceTest;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserServiceTest {
    @DataProvider
    public Object[][] testServiceFindUserById() {
        return new Object[][]{
                {1, "anna.t"},
                {2, "jack11"},
                {8, "raev"},
                {13, "objorik"}
        };
    }

    @Test(dataProvider = "testServiceFindUserById")
    public void testFindById(final int id, final String username) throws ServiceException {
        ConnectionPool.getInstance();
        UserService userService = new UserServiceImpl();
        User user = userService.findUserById(id);
        Assert.assertEquals(user.getUsername(), username);
    }

    @DataProvider
    public Object[][] findByUsernameAndPass() {
        return new Object[][]{
                {"jack11", "1Qazwsx"},
                {"raev", "1Qazwsx"},
                {"sparrow", "1Qazwsx"}
        };
    }

    @Test(dataProvider = "findByUsernameAndPass")
    public void findByUsernameAndPass(final String username, final String pass) throws ServiceException {
        ConnectionPool.getInstance();
        UserService userService = new UserServiceImpl();
        User user = userService.findByUsernameAndPass(username, pass).get(0);
        Assert.assertEquals(user.getUsername(), username);
    }

    @DataProvider
    public Object[][] findSupervisorOfUser() {
        return new Object[][]{
                {8, "jack11"},
                {13, "jack11"}
        };
    }

    @Test(dataProvider = "findSupervisorOfUser")
    public void findSupervisorOfUser(final int id, final String username) throws ServiceException {
        ConnectionPool.getInstance();
        UserService userService = new UserServiceImpl();
        User supervisor = userService.findSupervisorOfUser(id);
        Assert.assertEquals(username, supervisor.getUsername());
    }

}
