package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.OrderDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.model.Order;
import com.example.bookstoreapplication.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/insert")
    public Order insert(@Valid @RequestBody OrderDto orderDto){
        return orderService.insert(orderDto);
    }

    @GetMapping("/getAll")
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @GetMapping("/getById/{orderId}")
    public Order getById(@PathVariable int orderId){
        return orderService.getById(orderId);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<ResponseDto> delete(@PathVariable int orderId){
        return orderService.delete(orderId);
    }

    @PutMapping("/updateById/{orderId}")
    public Order updateById(@PathVariable int orderId,@Valid @RequestBody OrderDto orderDto){
        return orderService.updateById(orderId, orderDto);
    }
    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable int orderId){
        return orderService.cancelOrder(orderId);
    }
}
