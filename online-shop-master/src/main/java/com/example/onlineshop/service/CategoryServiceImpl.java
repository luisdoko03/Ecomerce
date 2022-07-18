package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveCategoryRequest;
import com.example.onlineshop.entity.Category;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.repository.CategoryRepository;
import com.example.onlineshop.repository.FileSystemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileSystemRepository fileSystemRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, FileSystemRepository fileSystemRepository) {
        this.categoryRepository = categoryRepository;
        this.fileSystemRepository = fileSystemRepository;
    }

    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(int id) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            return categoryOptional.get();
        }else{
       throw new CategoryNotFoundException();}
    }
public int save(SaveCategoryRequest request) {
        var dbCategory = categoryRepository.findById(request.getId());
        if(dbCategory.isPresent()){
            dbCategory.get().setName(request.getName());
            dbCategory.get().setPhotoUrl(request.getPhotoUrl());
            dbCategory.get().setModified(LocalDateTime.now());
            categoryRepository.save(dbCategory.get());
            return dbCategory.get().getId();
        }else {

            var newCategory = new Category();
            newCategory.setName(request.getName());
            newCategory.setPhotoUrl(request.getPhotoUrl());
            newCategory.setCreated(LocalDateTime.now());
            categoryRepository.save(newCategory);
            return newCategory.getId();
        }

}
    public Category insert(Category category) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        if(!categoryOptional.isPresent()) {
            category.setCreated(LocalDateTime.now());
            category.setModified(LocalDateTime.now());
            return categoryRepository.save(category);
        }else {
            throw new CategoryNotFoundException();
        }
    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
    public Category update(int id, Category category) throws CategoryNotFoundException {
        Optional<Category> categoryOptionalFromDb = categoryRepository.findById(id);
        if(categoryOptionalFromDb.isPresent()){
        Category categoryFromDb = categoryOptionalFromDb.get();
        categoryFromDb.setName(category.getName());
        categoryFromDb.setPhotoUrl(category.getPhotoUrl());
        categoryFromDb.setModified(LocalDateTime.now());
        categoryRepository.save(categoryFromDb);
        return categoryFromDb;
        }else{
            throw new CategoryNotFoundException();}
    }

    public Category findByName(String name) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findByName(name);
        if(categoryOptional.isPresent()){
            return categoryOptional.get();
        }else{
            throw new CategoryNotFoundException();}
    }
    }


