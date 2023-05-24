package com.babin.sportinggoodsstore.service;

import com.babin.sportinggoodsstore.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    void addToUserBucket(Long productId, String username); // добавляем продукт по Id к определенному пользователю
    void addProduct(ProductDTO dto);
}


