package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
}
