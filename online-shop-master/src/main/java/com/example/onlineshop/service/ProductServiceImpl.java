package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveProductRequest;
import com.example.onlineshop.entity.Category;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.exceptionHandler.ProductNotFoundException;
import com.example.onlineshop.repository.CategoryRepository;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryService;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryServiceImpl categoryService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }


    public List<Product> productList() {
        return productRepository.findAll();
    }

    public List<Product> productsByCategory(int id) {
        return productRepository.findProductByCategoryId(id);
    }

    public Product findById(int id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ProductNotFoundException();
        }
    }

    public int save(SaveProductRequest request) throws CategoryNotFoundException {
        var dbProduct = productRepository.findById(request.getId());
        if (dbProduct.isPresent()) {

            dbProduct.get().setTitle(request.getTitle());
            dbProduct.get().setPrice(request.getPrice());
            dbProduct.get().setQuantity(request.getQuantity());
            dbProduct.get().setDescription(request.getDescription());
            dbProduct.get().setFeatured(request.isFeatured());
            dbProduct.get().setPhotoUrl(request.getPhotoUrl());
            dbProduct.get().setModified(LocalDateTime.now());
            productRepository.save(dbProduct.get());
            return dbProduct.get().getId();
        } else {
            var newProduct = new Product();
            Category category = categoryRepository.findById(request.getCategoryId()).get();
            newProduct.setTitle(request.getTitle());
            newProduct.setPrice(request.getPrice());
            newProduct.setQuantity(request.getQuantity());
            newProduct.setDescription(request.getDescription());
            newProduct.setFeatured(request.isFeatured());
            newProduct.setPhotoUrl(request.getPhotoUrl());
            newProduct.setCreated(LocalDateTime.now());
            newProduct.setCategory(category);
            newProduct.setCategoryId(category.getId());
            productRepository.save(newProduct);
            return newProduct.getId();
        }

    }

    public Product insert(Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (!productOptional.isPresent()) {
            product.setCreated(LocalDateTime.now());
            product.setModified(LocalDateTime.now());
            return productRepository.save(product);
        } else {
            throw new ProductNotFoundException();
        }
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public Product update(int id, Product product) throws ProductNotFoundException, CategoryNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product productFromDb = productOptional.get();
            try {
                Category category = categoryService.findByName(product.getCategory().getName());
                productFromDb.setCategory(category);
            } catch (CategoryNotFoundException ex) {
                throw new CategoryNotFoundException();
            }
            productFromDb.setTitle(product.getTitle());
            productFromDb.setPrice(product.getPrice());
            productFromDb.setPhotoUrl(product.getPhotoUrl());
            productFromDb.setDescription(product.getDescription());
            productFromDb.setFeatured(product.isFeatured());
            productFromDb.setQuantity(product.getQuantity());
            productFromDb.setModified(LocalDateTime.now());
            productRepository.save(productFromDb);
            return productFromDb;
        } else {
            throw new ProductNotFoundException();
        }
    }
}
