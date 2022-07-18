package com.example.onlineshop.exceptionHandler;

public class FileUploadException extends RuntimeException{
    public FileUploadException() {

    }

    public FileUploadException(String message) {
        super(message);
    }
}
