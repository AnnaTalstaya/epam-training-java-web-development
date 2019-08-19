package serviceTest;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.RatingService;
import by.talstaya.crackertracker.service.impl.RatingServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RatingServiceTest {
    @DataProvider
    public Object[][] takeRatingByUser() {
        return new Object[][]{
                {8, 17, 4.0},
                {5, 2, 5.0},
                {8, 2, 3.0}

        };
    }

    @Test(dataProvider = "takeRatingByUser")
    public void takeRatingByUser(final int userId, final int supervisorId, final double rating) throws ServiceException {
        ConnectionPool.getInstance();
        RatingService ratingService = new RatingServiceImpl();

        Assert.assertEquals(rating, ratingService.takeRatingByUser(userId, supervisorId));
    }
}
