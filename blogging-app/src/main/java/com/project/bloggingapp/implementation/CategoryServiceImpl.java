package com.project.bloggingapp.implementation;

import com.project.bloggingapp.entities.Category;
import com.project.bloggingapp.exceptions.ResourceNotFoundException;
import com.project.bloggingapp.payloads.CategoryDto;
import com.project.bloggingapp.repositories.CategoryRepository;
import com.project.bloggingapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category newCategory = this.modelMapper.map(categoryDto, Category.class);
        Category savedCategory = this.categoryRepository.save(newCategory);
        CategoryDto response = this.modelMapper.map(savedCategory, CategoryDto.class);
        return response;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer cid) {
        Category foundCategory = this.categoryRepository.findById(cid).orElseThrow(()-> new ResourceNotFoundException("category ", " cid ", cid));
        foundCategory.setCategoryTitle(categoryDto.getCatTitle());
        foundCategory.setDescription(categoryDto.getDescription());
        Category changedCategory = this.categoryRepository.save(foundCategory);
        CategoryDto savedCategory = this.modelMapper.map(changedCategory, CategoryDto.class);
        return savedCategory;
    }

    @Override
    public void deleteCategory(Integer cid) {
        Category foundCategory = this.categoryRepository.findById(cid).orElseThrow(()-> new ResourceNotFoundException("category ", " cid ", cid));
        this.categoryRepository.delete(foundCategory);
    }

    @Override
    public CategoryDto getCategory(Integer cid) {
        Category foundCategory = this.categoryRepository.findById(cid).orElseThrow(()-> new ResourceNotFoundException("category ", " cid ", cid));
        CategoryDto returnCategory = this.modelMapper.map(foundCategory, CategoryDto.class);
        return returnCategory;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategories = this.categoryRepository.findAll();
        List<CategoryDto> allCategoryDtos = allCategories.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return allCategoryDtos;
    }
}
