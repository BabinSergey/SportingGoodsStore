package com.babin.sportinggoodsstore.dao;

import com.babin.sportinggoodsstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
