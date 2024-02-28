package com.yan.orderservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class OrderDto {
    private int user;
    private int menu;
}
