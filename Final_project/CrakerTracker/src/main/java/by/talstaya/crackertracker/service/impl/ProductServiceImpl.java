package by.talstaya.crackertracker.service.impl;

import by.talstaya.crackertracker.dao.ProductDao;
import by.talstaya.crackertracker.dao.product.ProductDaoImpl;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.DaoException;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.ProductService;

import java.util.List;

/**
 * This class is an implementation of ProductService
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    public ProductServiceImpl() {
        productDao = new ProductDaoImpl();
    }

    public List<Product> takeAllProducts() throws ServiceException {
        try {
            return productDao.takeAllProducts();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<Product> findByNameOrWordInNameWithLimit(String nameOrWordInName, int startIndex, int endIndex) throws ServiceException {
        try {
            return productDao.findByNameOrWordInNameWithLimit(nameOrWordInName, startIndex, endIndex);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findByNameOrWordInName(String nameOrWordInName) throws ServiceException {
        try {
            return productDao.findByNameOrWordInName(nameOrWordInName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findProductsByFilterWithLimit(String nameOrWordInName, int minCalories, int maxCalories,
                                                       int minProteins, int maxProteins,
                                                       int minLipids, int maxLipids,
                                                       int minCarbohydrates, int maxCarbohydrates,
                                                       int startIndex, int endIndex) throws ServiceException {
        try {
            if (nameOrWordInName.isEmpty()) {
                return productDao.findProductsByFilterWithoutSearchParamWithLimit(minCalories, maxCalories,
                        minProteins, maxProteins,
                        minLipids, maxLipids,
                        minCarbohydrates, maxCarbohydrates,
                        startIndex, endIndex);
            } else {
                return productDao.findProductsByFilterWithLimit(nameOrWordInName,
                        minCalories, maxCalories,
                        minProteins, maxProteins,
                        minLipids, maxLipids,
                        minCarbohydrates, maxCarbohydrates,
                        startIndex, endIndex);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findAllProductsByFilter(String nameOrWordInName, int minCalories, int maxCalories, int minProteins, int maxProteins, int minLipids, int maxLipids, int minCarbohydrates, int maxCarbohydrates) throws ServiceException {
        try {
            if (nameOrWordInName.isEmpty()) {
                return productDao.findProductsByFilterWithoutSearchParam(minCalories, maxCalories,
                        minProteins, maxProteins,
                        minLipids, maxLipids,
                        minCarbohydrates, maxCarbohydrates);
            } else {
                return productDao.findProductsByFilter(nameOrWordInName,
                        minCalories, maxCalories,
                        minProteins, maxProteins,
                        minLipids, maxLipids,
                        minCarbohydrates, maxCarbohydrates);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findProductsByLimit(int startIndex, int endIndex) throws ServiceException {
        try {
            return productDao.findProductsByLimit(startIndex, endIndex);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product findByProductId(int productId) throws ServiceException {
        try {
            return productDao.findById(productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findMinCalories() throws ServiceException {
        try {
            return productDao.findMinCalories();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findMinProteins() throws ServiceException {
        try {
            return productDao.findMinProteins();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findMinLipids() throws ServiceException {
        try {
            return productDao.findMinLipids();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findMinCarbohydrates() throws ServiceException {
        try {
            return productDao.findMinCarbohydrates();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findMaxCalories() throws ServiceException {
        try {
            return productDao.findMaxCalories();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findMaxProteins() throws ServiceException {
        try {
            return productDao.findMaxProteins();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findMaxLipids() throws ServiceException {
        try {
            return productDao.findMaxLipids();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findMaxCarbohydrates() throws ServiceException {
        try {
            return productDao.findMaxCarbohydrates();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int checkCalories(int minCalories) throws ServiceException {
        try {
            int minPossible = productDao.findMinCalories();
            int maxPossible = productDao.findMaxCalories();

            if (minCalories < minPossible) {
                return minPossible;
            } else if (minCalories > maxPossible) {
                return maxPossible;
            } else {
                return minCalories;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int checkProteins(int minProteins) throws ServiceException {
        try{
            int minPossible = productDao.findMinProteins();
            int maxPossible = productDao.findMaxProteins();

            if (minProteins < minPossible) {
                return minPossible;
            } else if (minProteins > maxPossible) {
                return maxPossible;
            } else {
                return minProteins;
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int checkLipids(int minLipids) throws ServiceException {
        try{
            int minPossible = productDao.findMinLipids();
            int maxPossible = productDao.findMaxLipids();

            if (minLipids < minPossible) {
                return minPossible;
            } else if (minLipids > maxPossible) {
                return maxPossible;
            } else {
                return minLipids;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int checkCarbohydrates(int minCarbohydrates) throws ServiceException {
        try{
            int minPossible = productDao.findMinCarbohydrates();
            int maxPossible = productDao.findMaxCarbohydrates();

            if (minCarbohydrates < minPossible) {
                return minPossible;
            } else if (minCarbohydrates > maxPossible) {
                return maxPossible;
            } else {
                return minCarbohydrates;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}
