package com.example.onlinestory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@TableGenerator(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
