package com.example.onlinestory.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateTime;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_product",
            joinColumns =  @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productList;
}
