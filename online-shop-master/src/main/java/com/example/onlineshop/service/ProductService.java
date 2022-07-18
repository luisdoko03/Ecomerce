package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveProductRequest;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.exceptionHandler.ProductNotFoundException;


import java.util.List;

public interface ProductService {

List<Product> productList();
    Product findById(int id) throws ProductNotFoundException;
    Product insert(Product product) throws ProductNotFoundException;
    void deleteById(int id);
    Product update(int id, Product product) throws ProductNotFoundException, CategoryNotFoundException;
    int save(SaveProductRequest request) throws CategoryNotFoundException;
    List<Product> productsByCategory(int id);


}
