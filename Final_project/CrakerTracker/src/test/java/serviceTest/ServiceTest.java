package serviceTest;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class ServiceTest {
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
    public void testFindById(final int id, final String username) throws SQLException, ServiceException {
        ConnectionPool.getInstance();
        UserService userService = new UserServiceImpl();
        User user = userService.findUserById(id);
        Assert.assertEquals(user.getUsername(), username);
    }
}
