package com.babin.sportinggoodsstore.service;

import com.babin.sportinggoodsstore.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService { // для security
    boolean save(UserDTO userDTO);
}
