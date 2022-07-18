package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Product;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.exceptionHandler.ProductNotFoundException;
import com.example.onlineshop.service.ProductService;
import com.example.onlineshop.dto.SaveProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/all")


    public List<Product> findAll() {
        return productService.productList();
    }

    @PostMapping
    public int save(@RequestBody SaveProductRequest request) throws CategoryNotFoundException {

        return productService.save(request);}



    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        try {
            return productService.findById(id);
        }catch (ProductNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }
    }
    @DeleteMapping("/{id}")
    private void delete(@PathVariable int id) {
        productService.deleteById(id);
    }
    @PutMapping("/{id}/edit")
    private Product update(@PathVariable int id, @RequestBody Product product) {
        try {
            return productService.update(id, product);
        } catch (ProductNotFoundException | CategoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product or Category Not found", ex);
        }
    }
}
