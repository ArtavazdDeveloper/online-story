package com.example.onlinestory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserType userType;
}
