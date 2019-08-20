package serviceTest;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ProductServiceTest {

    @DataProvider
    public Object[][] findByNameOrWordInName() {
        return new Object[][]{
                {"шоколад", 2},
                {"egg", 1},
                {"chocolate", 3}
        };
    }

    @Test(dataProvider = "findByNameOrWordInName")
    public void findByNameOrWordInName(final String name, final int size) throws ServiceException {
        ConnectionPool.getInstance();
        ProductService productService = new ProductServiceImpl();
        List<Product> products = productService.findByNameOrWordInNameWithLimit(name, 0, 8);
        Assert.assertEquals(size, products.size());
    }

}
