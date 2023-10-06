package com.example.bookstoreapplication.service;


import com.example.bookstoreapplication.dto.OrderDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.exception.OrderNotFoundException;
import com.example.bookstoreapplication.model.Order;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.OrderRepo;
import com.example.bookstoreapplication.repository.UserRepo;
import com.example.bookstoreapplication.util.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements iOrderService{

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    UserRepo userRepo;

//    (insert,getall,getbyID,delete,updatebyId, cancelOrder)(JMS)

    @Override
    public Order insert(OrderDto orderDto){
        Order order = new Order(orderDto);
        order.setDate(LocalDate.now());
        orderRepo.save(order);
        Optional<User> user = userRepo.findById(order.getUserID());
        emailSenderService.sendEmail(user.get().getEmail(), "Order placed.", "The order has been placed successfully for the books.");
        return order;
    }
    @Override
    public List<Order> getAll(){
        List<Order>orderList= orderRepo.findAll();
        return  orderList;
    }

    @Override
    public Order getById(int orderId){
        if (orderRepo.existsById(orderId)){
            return orderRepo.findById(orderId).get();
        }else {
            throw new OrderNotFoundException("Order not found. Check orderId.");
        }
    }

    @Override
    public ResponseEntity<ResponseDto> delete(int orderId){
        if (orderRepo.existsById(orderId)){
            orderRepo.deleteById(orderId);
            ResponseDto responseDto= new ResponseDto("Order Deleted", false);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }else{
            throw new OrderNotFoundException("Order not found. Check orderId.");
        }
    }
    @Override
    public Order updateById(int orderId, OrderDto orderDto){
        if (orderRepo.existsById(orderId)){
            Order order = new Order(orderDto);
            order.setOrderID(orderId);
            order.setDate(LocalDate.now());
            orderRepo.save(order);
            Optional<User> user = userRepo.findById(order.getUserID());
            emailSenderService.sendEmail(user.get().getEmail(), "Order Updated.", "The order has been updated successfully for the books.");
            return order;
        }else {
            throw new OrderNotFoundException("Order not found. Check orderId.");
        }
    }

    @Override
    public ResponseEntity<ResponseDto> cancelOrder(int orderId){
        if(orderRepo.existsById(orderId)){
            Order order = orderRepo.findById(orderId).get();
            Optional<User> user = userRepo.findById(order.getUserID());
            order.setOrderID(orderId);
            order.setCancel(true);
            orderRepo.save(order);
            ResponseDto responseDto = new ResponseDto("Order cancelled.", order);
            emailSenderService.sendEmail(user.get().getEmail(), "Order cancelled.", "The order has been cancelled successfully for the books.");
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }else {
            throw new OrderNotFoundException("Order not found. Check orderId.");
        }
    }
}
