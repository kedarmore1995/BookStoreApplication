package com.example.bookstoreapplication.service;


import com.example.bookstoreapplication.dto.CartDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.exception.CartNotFoundException;
import com.example.bookstoreapplication.model.Cart;
import com.example.bookstoreapplication.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements iCartService{

//    (insert,getall,getbyID,delete,updatebyID,updateQuantity)
    @Autowired
    CartRepo cartRepo;
    @Override
    public Cart insert(CartDto cartDto){
        Cart cart = new Cart(cartDto);
        cartRepo.save(cart);
        return cart;
    }
    @Override
    public List<Cart> getAll(){
        List<Cart> cartList= cartRepo.findAll();
        return  cartList;
    }

    @Override
    public ResponseEntity<ResponseDto> delete(int cartId){

        if (cartRepo.existsById(cartId)){
            cartRepo.deleteById(cartId);
        ResponseDto responseDto= new ResponseDto("Cart Deleted", false);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }else{
            throw new CartNotFoundException("Cart not found. Check cartId.");
        }
    }
    @Override
    public Cart updateById(int cartId, CartDto cartDto){
        if (cartRepo.existsById(cartId)){
            Cart cart = new Cart(cartDto);
            cart.setCartID(cartId);
            cartRepo.save(cart);
            return cart;
        }else {
            throw new CartNotFoundException("Cart not found. Check cartId.");
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateQuantity(int cartId, int quantity){
        if(cartRepo.existsById(cartId)){
            Cart cart =cartRepo.findById(cartId).get();
            cart.setQuantity(quantity);
            cartRepo.save(cart);
            ResponseDto responseDto = new ResponseDto(" Quantity Updated", cart );
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }else {
            throw new CartNotFoundException("No cart found. Check cartId.");
        }
    }

}
