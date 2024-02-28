package com.yan.orderservice.services;

import com.yan.orderservice.dto.OrderDto;
import com.yan.orderservice.exceptions.ResourceNotFoundException;
import com.yan.orderservice.models.Order;
import com.yan.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;

    public List<Order> findAll(){
        List<Order> order = orderRepository.findAll();
        if(!order.isEmpty()){
            return order;
        } else {
            throw new ResourceNotFoundException("Заказов еще нет");
        }
    }

    public Order findById(Long id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            return optionalOrder.get();
        } else {
            throw new ResourceNotFoundException("Такого заказа нет");
        }
    }

    public Order createOrder(OrderDto dto){
        Order order = new Order();
        order.setMenu(dto.getMenu());
        order.setUser(dto.getUser());
        return orderRepository.save(order);
    }
}
