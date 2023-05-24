package com.babin.sportinggoodsstore.controllers;

import com.babin.sportinggoodsstore.dto.ProductDTO;
import com.babin.sportinggoodsstore.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        List<ProductDTO> list = productService.getAll();
        model.addAttribute("products", list);
        return "products";
    }

    // метод добавляет продукт по Id корзины
    @GetMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal){
//        sessionObjectHolder.addClick();
        if(principal == null){
            return "redirect:/products";
        }
        productService.addToUserBucket(id, principal.getName());
        return "redirect:/products";
    }

    // добавление продуктов
    @PostMapping
    public ResponseEntity<Void> addProduct(ProductDTO dto){
        productService.addProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // отправляем продукты для всех видимости
    @MessageMapping("/products")
    public void messageAddProduct(ProductDTO dto){
        productService.addProduct(dto);
    }
}

