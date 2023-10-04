package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.CartDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.model.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface iCartService {
    public Cart insert(CartDto cartDto);

    public List<Cart> getAll();

    public ResponseEntity<ResponseDto> delete(int cartId);

    public Cart updateById(int cartId, CartDto cartDto);

    public ResponseEntity<ResponseDto> updateQuantity(int cartId, int quantity);

}
