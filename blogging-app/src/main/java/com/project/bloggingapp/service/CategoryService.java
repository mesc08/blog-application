package com.project.bloggingapp.service;

import com.project.bloggingapp.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer cid);

    void deleteCategory(Integer cid);

    CategoryDto getCategory(Integer cid);

    List<CategoryDto> getAllCategories();
}
