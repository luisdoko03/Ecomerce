package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveBlogRequest;
import com.example.onlineshop.entity.Post;
import com.example.onlineshop.exceptionHandler.BlogNotFoundException;

import java.util.List;

public interface BlogService {
    int save(SaveBlogRequest request);
    List<Post> blogList();
    Post findBlogById(int id) throws BlogNotFoundException;
    void deleteById(int id);
}
