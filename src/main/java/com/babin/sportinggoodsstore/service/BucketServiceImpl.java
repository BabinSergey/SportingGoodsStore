package com.babin.sportinggoodsstore.service;

import com.babin.sportinggoodsstore.dao.BucketRepository;
import com.babin.sportinggoodsstore.dao.ProductRepository;
import com.babin.sportinggoodsstore.dto.BucketDetailDto;
import com.babin.sportinggoodsstore.dto.BucketDto;
import com.babin.sportinggoodsstore.model.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final OrderService orderService;

    public BucketServiceImpl(BucketRepository bucketRepository,
                             ProductRepository productRepository,
                             UserService userService,
                             OrderService orderService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        bucket.setProduct(productList);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {
        return productIds.stream() // getOne - вытаскивает ссылку на продукт, findById - вытаскивает сам объект
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addProducts(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProduct();
        List<Product> newProductsList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductsList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProduct(newProductsList);
        bucketRepository.save(bucket);
    }

    // метод подсчета товаров добавленных в корзину для расчета
    @Override
    public BucketDto getBucketByUser(String name) {
        User user = userService.findByName(name);
        if(user == null || user.getBucket() == null){
            return new BucketDto();
        }

        BucketDto bucketDto = new BucketDto();
        Map<Long, BucketDetailDto> mapByProductId = new HashMap<>();

        List<Product> products = user.getBucket().getProduct();
        for (Product product : products) {
            BucketDetailDto detail = mapByProductId.get(product.getId());
            if(detail == null){
                mapByProductId.put(product.getId(), new BucketDetailDto(product));
            }
            else {
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                detail.setSum(detail.getSum() + Double.valueOf(product.getPrice().toString()));
            }
        }

        bucketDto.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDto.aggregate();

        return bucketDto;
    }

    @Override
    @Transactional
    public void commitBucketToOrder(String username) {
        User user = userService.findByName(username);
        if(user == null){
            throw new RuntimeException("Пользователь не найден");
        }
        Bucket bucket = user.getBucket();
        if(bucket == null || bucket.getProduct().isEmpty()){
            return;
        }

        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setUser(user);

        Map<Product, Long> productWithAmount = bucket.getProduct().stream()
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()));

        List<OrderDetails> orderDetails = productWithAmount.entrySet().stream()
                .map(pair -> new OrderDetails(order, pair.getKey(), pair.getValue()))
                .collect(Collectors.toList());

        BigDecimal total = new BigDecimal(orderDetails.stream()
                .map(detail -> detail.getPrice().multiply(detail.getAmount()))
                .mapToDouble(BigDecimal::doubleValue).sum());

        order.setDetails(orderDetails);
        order.setSum(total);
        order.setAddress("none");

        orderService.saveOrder(order);
        bucket.getProduct().clear();
        bucketRepository.save(bucket);
    }
}
