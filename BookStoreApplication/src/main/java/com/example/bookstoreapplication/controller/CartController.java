package com.example.bookstoreapplication.controller;


import com.example.bookstoreapplication.dto.CartDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.model.Cart;
import com.example.bookstoreapplication.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")

public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/insert")
    public Cart insert(@Valid @RequestBody CartDto cartDto){
        return cartService.insert(cartDto);
    }

    @GetMapping("/getAll")
    public List<Cart> getAll(){
        return cartService.getAll();
    }

    @DeleteMapping("/deleteById/{cartId}")
    public ResponseEntity<ResponseDto> delete(@PathVariable int cartId){
        return cartService.delete(cartId);
    }

    @PutMapping("/updateById/{cartId}")
    public Cart updateById(@PathVariable int cartId, @Valid @RequestBody CartDto cartDto){
        return  cartService.updateById(cartId, cartDto);
    }

    @PutMapping("/updateQuantity/{cartId}")
    public ResponseEntity<ResponseDto> updateQuantity(@PathVariable int cartId,@RequestParam int quantity){
        return cartService.updateQuantity(cartId, quantity);
    }
}
