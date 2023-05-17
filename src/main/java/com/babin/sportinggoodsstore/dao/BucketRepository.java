package com.babin.sportinggoodsstore.dao;

import com.babin.sportinggoodsstore.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}