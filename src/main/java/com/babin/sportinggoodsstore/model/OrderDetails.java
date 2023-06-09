package com.babin.sportinggoodsstore.model;

// класс детали заказа

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="orders_details")
public class OrderDetails {
    private static final String SEQ_NAME = "order_details_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    @ManyToOne                                // множество деталий к заказу
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne                                // множество товаров в заказе
    @JoinColumn(name = "product_id")
    private Product product;
    private BigDecimal amount;                // количество
    private BigDecimal price;                 // цена

    public OrderDetails(Order order, Product product, Long amount) {
        this.order = order;
        this.product = product;
        this.amount = new BigDecimal(amount);
        this.price = new BigDecimal(String.valueOf(product.getPrice()));
    }
}
