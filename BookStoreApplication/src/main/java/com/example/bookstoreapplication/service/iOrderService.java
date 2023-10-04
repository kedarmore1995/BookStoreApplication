package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.OrderDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.model.Order;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface iOrderService {
    public Order insert(OrderDto orderDto);
    public List<Order> getAll();
    public Order getById(int orderId);
    public ResponseEntity<ResponseDto> delete(int orderId);
    public Order updateById(int orderId, OrderDto orderDto);
    public ResponseEntity<ResponseDto> cancelOrder(int orderId);

}
