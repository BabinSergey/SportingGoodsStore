package com.babin.sportinggoodsstore.service;

import com.babin.sportinggoodsstore.dto.BucketDto;
import com.babin.sportinggoodsstore.model.Bucket;
import com.babin.sportinggoodsstore.model.User;

import java.util.List;

public interface BucketService {
    // создаем корзину по User
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDto getBucketByUser(String name); // поиск bucket по user

    void commitBucketToOrder(String username);
}
