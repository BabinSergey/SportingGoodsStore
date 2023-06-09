package com.babin.sportinggoodsstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// класс пользователя

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {

    private static final String SEQ_NAME = "user_seq"; // название последовательности

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;                              // Id
    private String name;                          // Имя
    private String surname;                       // Фамилия
    private String city;                          // Город
    private String country;                       // Страна
    private String phone;                         // Телефон
    private String email;                         // Email
    private String password;                      // Пароль
    private boolean locked;                       // Заблокированный или нет
    @Enumerated(EnumType.STRING)                  // Роль
    private Role role;
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)       // Один пользователь одна корзина
    private Bucket bucket;

}
