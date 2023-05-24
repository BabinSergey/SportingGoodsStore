package com.babin.sportinggoodsstore.dao;

import com.babin.sportinggoodsstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);   // поиск по имени
}
