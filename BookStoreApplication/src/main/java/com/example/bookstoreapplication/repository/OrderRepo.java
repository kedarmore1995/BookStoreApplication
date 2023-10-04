package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
