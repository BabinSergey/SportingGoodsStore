package com.babin.sportinggoodsstore.service;

import com.babin.sportinggoodsstore.dao.OrderRepository;
import com.babin.sportinggoodsstore.model.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
//    private final OrderIntegrationConfig integrationConfig;

    public OrderServiceImpl(OrderRepository orderRepository) { // , OrderIntegrationConfig integrationConfig
        this.orderRepository = orderRepository;
//        this.integrationConfig = integrationConfig;
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);

//        Order savedOrder = orderRepository.save(order);
//        sendIntegrationNotify(savedOrder);
    }

//    private void sendIntegrationNotify(Order order){
//        OrderIntegrationDto dto = new OrderIntegrationDto();
//        dto.setUsername(order.getUser().getName());
//        dto.setAddress(order.getAddress());
//        dto.setOrderId(order.getId());
//        List<OrderIntegrationDto.OrderDetailsDto> details = order.getDetails().stream()
//                .map(OrderIntegrationDto.OrderDetailsDto::new).collect(Collectors.toList());
//        dto.setDetails(details);
//
//        Message<OrderIntegrationDto> message = MessageBuilder.withPayload(dto)
//                .setHeader("Content-type", "application/json")
//                .build();
//
//        integrationConfig.getOrdersChannel().send(message);
//    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
