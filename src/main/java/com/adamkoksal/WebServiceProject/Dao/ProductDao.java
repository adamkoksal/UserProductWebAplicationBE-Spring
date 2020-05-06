package com.adamkoksal.WebServiceProject.Dao;

import com.adamkoksal.WebServiceProject.Entity.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {
    Connection myConn;
    Statement myStmt;
    List<Product> products;

    public ProductDao() throws SQLException {
        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_website","root","canonmx300");
        myStmt = myConn.createStatement();
    }

    public void refreshProductList() throws SQLException {
        products = new ArrayList<>();
        ResultSet myRs = myStmt.executeQuery("SELECT * FROM my_website.products");

        while (myRs.next()) {
            Product product = new Product();
            product.setId(myRs.getInt(1));
            product.setName(myRs.getString(2));
            product.setCategory(myRs.getInt(3));
            product.setPrice(myRs.getDouble(4));
            product.setQuantity(myRs.getInt(5));
            products.add(product);
        }
    }

    public List<Product> getProducts() throws SQLException {
        refreshProductList();
        return products;
    }


    public Product getProductById(int id) throws SQLException {
        refreshProductList();
        Optional<Product> filteredProduct = products.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        return filteredProduct.orElse(null);
    }

    public void deleteProduct(int id) throws SQLException {
        String sql = String.format("DELETE FROM products WHERE id = \"%s\" ", id);
        myStmt.executeUpdate(sql);
    }

    public void updateProduct(int id, Product product) throws SQLException {
        String sql = String.format(
                "UPDATE products SET name = \"%s\", category = \"%s\", price = \"%s\", quantity = \"%s\" WHERE id = \"%s\"  ",
                product.getName(),product.getCategory(), product.getPrice(), product.getQuantity(), id);
        myStmt.executeUpdate(sql);
    }

    public void createProduct(Product product) throws SQLException {
        String sql = String.format(
                "INSERT INTO products (name, category, price, quantity) VALUES( \"%s\", \"%s\", \"%s\", \"%s\")",
                product.getName(),product.getCategory(), product.getPrice(), product.getQuantity());
        myStmt.executeUpdate(sql);

    }
}
