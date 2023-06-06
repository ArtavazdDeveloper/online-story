package com.example.onlinestory.controller;

import com.example.onlinestory.entity.Category;
import com.example.onlinestory.entity.Product;
import com.example.onlinestory.security.CurrentUser;
import com.example.onlinestory.service.impl.CategoryServiceImpl;
import com.example.onlinestory.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private ProductServiceImpl productServiceImpl;
    private CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/add")
    public String addProductPage(ModelMap modelMap){
        List<Product> productList = productServiceImpl.productList();
        List<Category> categoryList = categoryServiceImpl.categorytList();
        modelMap.addAttribute("products",productList);
        modelMap.addAttribute("categories",categoryList);
        return "productAdd";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("image") MultipartFile multipartFile,
                             @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        productServiceImpl.addProduct(product, multipartFile);
        return "redirect:/user/admin";
    }

    @GetMapping("/remove")
    public String productRemove(@RequestParam("id") int id) {
        productServiceImpl.deleteProductById(id);
        return "redirect:/user/admin";
    }
}
