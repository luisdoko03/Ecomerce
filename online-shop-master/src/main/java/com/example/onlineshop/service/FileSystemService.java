package com.example.onlineshop.service;

import org.springframework.core.io.FileSystemResource;

public interface FileSystemService {

    FileSystemResource findImage(String photoUrl);
}
