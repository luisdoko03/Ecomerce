package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Category;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.service.CategoryService;
import com.example.onlineshop.service.FileSystemService;
import com.example.onlineshop.dto.SaveCategoryRequest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final FileSystemService fileSystemService;

    public CategoryController(CategoryService categoryService, FileSystemService fileSystemService) {
        this.categoryService = categoryService;
        this.fileSystemService = fileSystemService;
    }

    @GetMapping("/all")
    public List<Category> findAll() {
        return categoryService.categoryList();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") int id) {

        try {
            return categoryService.findCategoryById(id);

        } catch (CategoryNotFoundException c) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found", c);
        }

    }
    @PostMapping
    public int save(@RequestBody SaveCategoryRequest request) {
      return categoryService.save(request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        categoryService.deleteById(id);
    }
    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    FileSystemResource downloadImage(@RequestBody String photoUrl) throws Exception {
        return fileSystemService.findImage(photoUrl);
    }
}
