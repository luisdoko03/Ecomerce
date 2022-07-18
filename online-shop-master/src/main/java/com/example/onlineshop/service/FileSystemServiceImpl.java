package com.example.onlineshop.service;

import com.example.onlineshop.repository.FileSystemRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class FileSystemServiceImpl implements FileSystemService{
    private final FileSystemRepository fileSystemRepository;

    public FileSystemServiceImpl(FileSystemRepository fileSystemRepository) {
        this.fileSystemRepository = fileSystemRepository;
    }

    @Override
    public FileSystemResource findImage(String photoUrl) {
        return fileSystemRepository.findInFileSystem(photoUrl);
    }
}
