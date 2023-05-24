package com.babin.sportinggoodsstore.service;

import com.babin.sportinggoodsstore.model.Order;

public interface OrderService {
    void saveOrder(Order order);

    Order getOrder(Long id);
}
