package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveCategoryRequest;
import com.example.onlineshop.entity.Category;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> categoryList();
    Category findCategoryById(int id) throws CategoryNotFoundException;
    Category insert(Category category) throws CategoryNotFoundException;
    void deleteById(int id);
    Category update(int id, Category category) throws CategoryNotFoundException;
    int save(SaveCategoryRequest request);

}
