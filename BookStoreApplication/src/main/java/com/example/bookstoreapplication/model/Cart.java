package com.example.bookstoreapplication.model;


import com.example.bookstoreapplication.dto.CartDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int cartID;
    public int userID;
    public int bookID;
    public int quantity;

    public Cart (CartDto cartDto){
        this.userID = cartDto.userID;
        this.bookID = cartDto.bookID;
        this.quantity = cartDto.quantity;
    }

}
