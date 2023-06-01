package com.example.onlinestory.service;

import com.example.onlinestory.entity.Cart;

import java.util.Optional;

public interface CartService {
    Optional<Cart> findCartByUserId(int id);
    void addCart(Cart cart);
}
