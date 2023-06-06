package com.example.onlinestory.service.impl;

import com.example.onlinestory.entity.Category;
import com.example.onlinestory.repository.CategoryRepository;
import com.example.onlinestory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public void addCategory(Category category)  {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> categorytList() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

}
