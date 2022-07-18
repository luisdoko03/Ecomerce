package com.example.onlineshop.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Blog not found")
public class BlogNotFoundException extends Exception{
}
