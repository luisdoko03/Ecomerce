package com.example.onlineshop.repository;

import com.example.onlineshop.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Post,Integer> {
}
