package com.example.onlineshop.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Client With Active Order")
public class ClientWithOrderException extends Exception{

}
