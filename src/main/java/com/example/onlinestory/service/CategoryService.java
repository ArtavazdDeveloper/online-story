package com.example.onlinestory.service;

import com.example.onlinestory.entity.Category;

import java.io.IOException;
import java.util.List;


public interface CategoryService {
    void addCategory(Category category) throws IOException;
    List<Category> categorytList();
}
