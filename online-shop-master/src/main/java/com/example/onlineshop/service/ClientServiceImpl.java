package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveClient;
import com.example.onlineshop.entity.Client;
import com.example.onlineshop.entity.Order;
import com.example.onlineshop.exceptionHandler.ClientNotFoundException;
import com.example.onlineshop.exceptionHandler.ClientWithOrderException;
import com.example.onlineshop.repository.ClientRepository;
import com.example.onlineshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    public ClientServiceImpl(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }



    public List<Client> clientList() {
        return clientRepository.findAll();
    }

    public Client findById(int id) throws ClientNotFoundException {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isPresent()){
            return clientOptional.get();
        }else {
            throw new ClientNotFoundException();
        }
    }
    public Client insert(Client client) throws ClientNotFoundException {
        Optional<Client> checkEmail = clientRepository.findByEmail(client.getEmail());
        if(!checkEmail.isPresent()) {
            client.setModified(LocalDateTime.now());
            client.setCreated(LocalDateTime.now());
            return clientRepository.save(client);
        }else
            throw new ClientNotFoundException();
    }

    public void deleteById(int id) throws ClientWithOrderException {

        List<Order> orderList = orderRepository.findAll();
        orderList.stream().filter(o ->
                o.getClient().getId()==id
        ).collect(Collectors.toList());
            if (!orderList.isEmpty()){
            clientRepository.deleteById(id);
        }
        else{
            throw new ClientWithOrderException();
        }
    }
    public Client update(int id, Client client) throws ClientNotFoundException {

        Optional<Client> clientFromDb = clientRepository.findById(id);
        Optional<Client> clientWithEmail = clientRepository.findByEmail(client.getEmail());
        if (clientFromDb.isPresent() && clientWithEmail.isPresent() && clientWithEmail.get().equals(clientFromDb.get())) {

                Client clientToUpdate = clientFromDb.get();
                clientToUpdate.setEmail(client.getEmail());
                clientToUpdate.setFirstName(client.getFirstName());
                clientToUpdate.setLastName(client.getLastName());
                clientToUpdate.setPhoneNumber(client.getPhoneNumber());
                clientToUpdate.setModified(LocalDateTime.now());
                return clientRepository.save(clientToUpdate);

        } else {
            throw new ClientNotFoundException();
        }
    }

    public int save(SaveClient request) {
        var dbClient = clientRepository.findById(request.getId());
        if (dbClient.isPresent()) {

            dbClient.get().setFirstName(request.getFirstName());
            dbClient.get().setLastName(request.getLastName());
            dbClient.get().setEmail(request.getEmail());
            dbClient.get().setPhoneNumber(request.getPhoneNumber());
            dbClient.get().setModified(LocalDateTime.now());
            clientRepository.save(dbClient.get());
            return dbClient.get().getId();
        } else {
            Client client = new Client();
            client.setFirstName(request.getFirstName());
            client.setLastName(request.getLastName());
            client.setEmail(request.getEmail());
            client.setPhoneNumber(request.getPhoneNumber());
            client.setCreated(LocalDateTime.now());
            return clientRepository.save(client).getId();

        }
    }

}
