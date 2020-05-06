package com.adamkoksal.WebServiceProject.Service;

import com.adamkoksal.WebServiceProject.Dao.ProductDao;
import com.adamkoksal.WebServiceProject.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;


    public List<Product> getProducts() throws SQLException {
        return productDao.getProducts();
    }

    public Product getProductById(int id) throws SQLException  {
        return productDao.getProductById(id);
    }


    public void deleteProduct(int id) throws SQLException {
        productDao.deleteProduct(id);
    }

    public void updateProduct(int id, Product product) throws SQLException {
        productDao.updateProduct(id, product);
    }

    public void createProduct(Product product) throws SQLException {
        productDao.createProduct(product);
    }
}
