package com.esd.inventory.service;

import com.esd.inventory.DAO.ProductDAO;

import com.esd.inventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {



    @Autowired
    ProductDAO productDAO;

    public List<Product> getAllProduct(){
        return productDAO.findAll();
    }

    public void addProduct(Product product){
        productDAO.save(product);
    }

    public void updateProduct(Product product){
        productDAO.update(product);
    }

    public void removeProductById(long id){
        productDAO.deleteById(id);
    }

    public Product getProductById(long id){
        return  productDAO.findById(id);
    }

    public List<Product> getAllProductsByCategoryId(int id){
        return productDAO.findAllByCategory_Id(id);
    }

}
