package com.babin.sportinggoodsstore.entity;

// класс продукта

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="products")
public class Product {

    private static final String SEQ_NAME = "product_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String title;
    private BigDecimal price;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_categories",
            joinColumns =  @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_brands",
            joinColumns =  @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "brands_id"))
    private List<Brand> brands;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_colors",
            joinColumns =  @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "colors_id"))
    private List<Colors> colors;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_colors",
            joinColumns =  @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "sizes_id"))
    private List<Sizes> sizes;



}
