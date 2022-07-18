package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveBlogRequest;
import com.example.onlineshop.entity.Post;
import com.example.onlineshop.exceptionHandler.BlogNotFoundException;
import com.example.onlineshop.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public int save(SaveBlogRequest request) {
        var dbBlog = blogRepository.findById(request.getId());
        if (dbBlog.isPresent()) {
            dbBlog.get().setTitle(request.getTitle());
            dbBlog.get().setAuthor(request.getAuthor());
            dbBlog.get().setContent(request.getContent());
            dbBlog.get().setPhoto(request.getPhoto());
            dbBlog.get().setModified(LocalDateTime.now());
            blogRepository.save(dbBlog.get());
            return dbBlog.get().getId();
        } else {

            var newBlog = new Post();
            newBlog.setTitle(request.getTitle());
            newBlog.setAuthor(request.getAuthor());
            newBlog.setContent(request.getContent());
            newBlog.setPhoto(request.getPhoto());
            newBlog.setCreated(LocalDateTime.now());
            blogRepository.save(newBlog);
            return newBlog.getId();
        }
    }

    public List<Post> blogList() {
        return blogRepository.findAll();
    }

    public Post findBlogById(int id) throws BlogNotFoundException {
        Optional<Post> blogOptional = blogRepository.findById(id);
        if (blogOptional.isPresent()) {
            return blogOptional.get();
        } else {
            throw new BlogNotFoundException();
        }
    }

    public void deleteById(int id) {
        blogRepository.deleteById(id);
    }
}
