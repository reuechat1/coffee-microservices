package com.yan.orderservice.controllers;

import com.yan.orderservice.dto.OrderDto;
import com.yan.orderservice.models.Order;
import com.yan.orderservice.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("Заказ создан");
        return orderService.createOrder(dto);
    }
}
