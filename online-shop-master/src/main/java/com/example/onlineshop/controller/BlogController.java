package com.example.onlineshop.controller;

import com.example.onlineshop.dto.SaveBlogRequest;
import com.example.onlineshop.entity.Post;
import com.example.onlineshop.exceptionHandler.BlogNotFoundException;
import com.example.onlineshop.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public int save(@RequestBody SaveBlogRequest request) {
        return blogService.save(request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        blogService.deleteById(id);
    }

    @GetMapping("/all")
    public List<Post> findAll() {
        return blogService.blogList();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable("id") int id) {
        try {
            return blogService.findBlogById(id);
        } catch (BlogNotFoundException c) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Banner Not Found", c);
        }
    }
}
