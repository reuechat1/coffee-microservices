package com.yan.orderservice.controllers;

import com.yan.orderservice.dto.OrderDto;
import com.yan.orderservice.models.Order;
import com.yan.orderservice.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/findAll")
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/findById")
    public Order findById(@RequestParam Long id){
        return orderService.findById(id);
    }

    @PostMapping("/add")
    public Order createOrder(@RequestBody OrderDto dto){
        return orderService.createOrder(dto);
    }
}
