package com.example.onlinestory.repository;

import com.example.onlinestory.entity.Cart;
import com.example.onlinestory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findCartByUserId(int id);
}
