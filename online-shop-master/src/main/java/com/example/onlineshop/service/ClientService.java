package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveClient;
import com.example.onlineshop.entity.Client;
import com.example.onlineshop.exceptionHandler.ClientNotFoundException;
import com.example.onlineshop.exceptionHandler.ClientWithOrderException;

import java.util.List;

public interface ClientService {
    List<Client> clientList();
    Client findById(int id) throws ClientNotFoundException;
    Client insert(Client client) throws ClientNotFoundException;
    void deleteById(int id) throws ClientWithOrderException;
    Client update(int id, Client client) throws ClientNotFoundException;

    int save(SaveClient client);

}
