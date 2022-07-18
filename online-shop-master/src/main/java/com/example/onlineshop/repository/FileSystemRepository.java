package com.example.onlineshop.repository;

import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import org.springframework.core.io.FileSystemResource;

public interface FileSystemRepository {
    String save(byte[] content, String imageName) throws CategoryNotFoundException, Exception;

    FileSystemResource findInFileSystem(String location);
}
