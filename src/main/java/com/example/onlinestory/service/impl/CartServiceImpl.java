package com.example.onlinestory.service.impl;

import com.example.onlinestory.entity.Cart;
import com.example.onlinestory.repository.CartRepository;
import com.example.onlinestory.service.CartService;

import java.util.Optional;

public class CartServiceImpl implements CartService {

   private CartRepository cartRepository;
    @Override
    public Optional<Cart> findCartByUserId(int id) {
        return cartRepository.findCartByUserId(id);
    }

    @Override
    public void addCart(Cart cart) {
        cartRepository.save(cart);
    }
}
