package com.example.bookstoreapplication.model;


import com.example.bookstoreapplication.dto.OrderDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int orderID;
    public LocalDate date;
    public int totalPrice;
    public int quantity;
    public String address;
    public int userID;
    public int bookID;
    public boolean cancel;

    public Order (OrderDto orderDto){
        this.date = orderDto.date;
        this.totalPrice=orderDto.totalPrice;
        this.quantity=orderDto.quantity;
        this.address=orderDto.address;
        this.userID=orderDto.userID;
        this.bookID=orderDto.bookID;
        this.cancel=orderDto.cancel;
    }
}
