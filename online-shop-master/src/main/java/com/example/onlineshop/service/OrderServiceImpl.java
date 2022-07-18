package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveClient;
import com.example.onlineshop.dto.SaveOrderClient;
import com.example.onlineshop.dto.SaveOrderRequest;
import com.example.onlineshop.entity.Client;
import com.example.onlineshop.entity.Order;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.exceptionHandler.OrderNotFoundException;
import com.example.onlineshop.repository.ClientRepository;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ClientService clientService;

    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository, ProductRepository productRepository, ClientService clientService) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;

        this.clientService = clientService;
    }

    public List<Order> orderList() {
        return orderRepository.findAll();
    }

    public Order findById(int id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException());
    }
    public List<Order> findOrdersByClientId(int id) {
        return orderRepository.findOrdersByClientId(id);
    }

    public int save(SaveOrderRequest order) {
        var dbOrder = orderRepository.findById(order.getId());
        if (dbOrder.isPresent()) {

            dbOrder.get().setClient(clientRepository.findById(order.getClientId()).get());
            dbOrder.get().setComment(order.getComment());
            dbOrder.get().setProduct(productRepository.findById(order.getProductId()).get());
            dbOrder.get().setFinalPrice(order.getFinalPrice());
            dbOrder.get().setQuantity(order.getQuantity());
            dbOrder.get().setModified(LocalDateTime.now());
            orderRepository.save(dbOrder.get());
            return dbOrder.get().getId();
        } else {
            Order orderToSave = new Order();
            Client client = clientRepository.findById(order.getClientId()).get();
            Product product = productRepository.findById(order.getProductId()).get();
            orderToSave.setFinalPrice(order.getFinalPrice());
            orderToSave.setComment(order.getComment());
            orderToSave.setProduct(product);
            orderToSave.setProductId(product.getId());
            orderToSave.setQuantity(order.getQuantity());
            orderToSave.setClient(client);
            orderToSave.setClientId(client.getId());
            orderToSave.setCreated(LocalDateTime.now());
            orderRepository.save(orderToSave);
            return 1;

        }
    }

    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    public void saveOrderAndClient(SaveOrderClient orderClient) {
        SaveClient client = new SaveClient();
        client.setFirstName(orderClient.getFirstName());
        client.setLastName(orderClient.getLastName());
        client.setEmail(orderClient.getEmail());
        client.setPhoneNumber(orderClient.getPhoneNumber());
        int clientId = clientService.save(client);

        SaveOrderRequest request = new SaveOrderRequest();
        request.setId(orderClient.getId());
        request.setProductId(orderClient.getProductId());
        request.setQuantity(orderClient.getQuantity());
        request.setFinalPrice(orderClient.getFinalPrice());
        request.setClientId(clientId);

        save(request);
    }
}
