package com.babin.sportinggoodsstore.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String username;              // имя
    private String password;              // пароль
    private String matchingPassword;      // подтверждение пароля
    private String email;                 // email
}
