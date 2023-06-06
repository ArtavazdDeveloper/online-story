package com.example.onlinestory.controller;

import com.example.onlinestory.entity.*;
import com.example.onlinestory.security.CurrentUser;
import com.example.onlinestory.repository.CartRepository;
import com.example.onlinestory.service.impl.CartServiceImpl;
import com.example.onlinestory.service.impl.OrderServiceImpl;
import com.example.onlinestory.service.impl.ProductServiceImpl;
import com.example.onlinestory.utill.ProductUtill;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private  CartServiceImpl cartServiceImpl;
    private  ProductServiceImpl productServiceImpl;
    private  OrderServiceImpl orderServiceImpl;
    @GetMapping("/add")
    public String addProductByCart(@AuthenticationPrincipal CurrentUser currentUser,
                                   @RequestParam("productId") int productId) {
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cartServiceImpl.findCartByUserId(user.getId());
        Optional<Product> byId = productServiceImpl.findById(productId);
        if (cartByUserId.isPresent()) {
            Cart cart = cartByUserId.get();
            cart.getProductList().add(byId.get());
            cartServiceImpl.addCart(cart);
        }
        return "redirect:/";
    }
    @GetMapping("/see/context")
    public String order(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cartServiceImpl.findCartByUserId(user.getId());
        Set<Product> productList = cartByUserId.get().getProductList();
        modelMap.addAttribute("totalSum", ProductUtill.countPrice(productList));
        modelMap.addAttribute("userProducts", productList);
        return "orders";
    }
    @GetMapping("do/order")
    public String order(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cartServiceImpl.findCartByUserId(user.getId());
        if (cartByUserId.isPresent()) {
            Cart cart = cartByUserId.get();
            Order order = Order.builder()
                    .user(user)
                    .dateTime(new Date())
                    .productList(new LinkedHashSet<>(cart.getProductList()))
                    .build();
            orderServiceImpl.addOrder(order);
            cartServiceImpl.addCart(cart);
        }
        return "redirect:/";

    }
    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable("productId") int id,
                                 @AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cartServiceImpl.findCartByUserId(user.getId());
        if (cartByUserId.isPresent()){
            Cart cart = cartByUserId.get();
            Set<Product> updatedProducts = ProductUtill.deleteById(cart.getProductList(), id);
            cart.setProductList(updatedProducts);
            cartServiceImpl.addCart(cart);
        }
        return "redirect:/cart/see/context";

    }

    public CartServiceImpl getCartService() {
        return cartServiceImpl;
    }
}
