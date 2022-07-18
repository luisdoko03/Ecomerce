package com.example.onlineshop.repository;

import com.example.onlineshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
List<Order> findOrdersByClientId(int id);
}
