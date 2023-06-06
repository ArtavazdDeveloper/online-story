package com.example.onlinestory.controller;

import com.example.onlinestory.entity.Product;
import com.example.onlinestory.entity.User;
import com.example.onlinestory.entity.UserType;
import com.example.onlinestory.security.CurrentUser;
import com.example.onlinestory.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private ProductServiceImpl productServiceImpl;
    @Value("${onlianestory.upload.image.path}")
    private String imageUploadPate;

    @GetMapping("/")
    public String productPage(ModelMap modelMap) {
        List<Product> productList = productServiceImpl.productList();
        modelMap.addAttribute("products", productList);
        return "index";
    }

    @GetMapping("/customSuccessLogin")
    public String customSuccessLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getUserType() == UserType.ADMIN) {
                return "redirect:/user/admin";
            } else if (user.getUserType() == UserType.USER) {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/customLogin")
    public String customLogin() {
        return "customLoginPage";
    }

    @GetMapping(value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("imagPath") String imgPath) throws IOException {
        File file = new File(imageUploadPate + imgPath);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }
}