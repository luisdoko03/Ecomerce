package com.example.onlineshop.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product Not Found")
public class ProductNotFoundException extends Exception{

}
