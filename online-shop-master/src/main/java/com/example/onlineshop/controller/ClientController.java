package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Category;
import com.example.onlineshop.entity.Client;
import com.example.onlineshop.exceptionHandler.ClientNotFoundException;
import com.example.onlineshop.exceptionHandler.ClientWithOrderException;
import com.example.onlineshop.service.ClientService;
import com.example.onlineshop.service.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")

    public List<Client> findAll() {
        return clientService.clientList();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable("id") int id) {
        try {
            return clientService.findById(id);

        } catch (ClientNotFoundException ex) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found", ex);
        }
    }

    @PostMapping("/new")
    public Client save(@RequestBody Client client) throws ClientNotFoundException {
        try {
            return clientService.insert(client);
        } catch (ClientNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already in use", e);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        try {
            clientService.deleteById(id);
        }
        catch (ClientWithOrderException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "can not delete the client with active order", e);
        }
    }
//
//    @PutMapping("/{id}/update")
//    public Client update (@PathVariable int id, @RequestBody Client client) {
//        try {
//            return clientService.update(id, client);
//        } catch (ClientNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "please provide correct data");
//        }
//    }
}
