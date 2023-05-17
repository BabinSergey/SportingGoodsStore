package com.babin.sportinggoodsstore.service;

import com.babin.sportinggoodsstore.dto.UserDTO;
import com.babin.sportinggoodsstore.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService { // для security
    boolean save(UserDTO userDTO);

    void save(User user);

    List<UserDTO> getAll();

    User findByName(String name);
    void updateProfile(UserDTO userDTO);
}
