package com.babin.sportinggoodsstore.entity;

// класс заказа

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="orders")
public class Order {
    private static final String SEQ_NAME = "order_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    @CreationTimestamp                               // анотация текущая дата
    private LocalDateTime created;                   // дата создания заказа
    @UpdateTimestamp                                 // анотация дата обновления
    private LocalDateTime updated;                   // дата обновления заказа
    @ManyToOne                                       // много заказов к одному пользователю
    @JoinColumn(name = "user_id")
    private User user;
    private BigDecimal sum;                          // сумма заказа
    private String address;                          // адрес
    @OneToMany(cascade = CascadeType.ALL)            // один заказ для множества продуктов
    private List<OrderDetails> details;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
