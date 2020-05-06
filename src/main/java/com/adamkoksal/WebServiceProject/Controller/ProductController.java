package com.adamkoksal.WebServiceProject.Controller;

import com.adamkoksal.WebServiceProject.Entity.Product;
import com.adamkoksal.WebServiceProject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getProducts() throws SQLException {
        return productService.getProducts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") int id) throws SQLException {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") int id) throws SQLException {
        productService.deleteProduct(id);
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable("id") int id, @RequestBody Product product) throws SQLException {
        productService.updateProduct(id, product);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createProduct(@RequestBody Product product) throws SQLException {
        productService.createProduct(product);
    }













}
