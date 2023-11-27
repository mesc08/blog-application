package com.project.bloggingapp.controllers;

import com.project.bloggingapp.utils.AppConstants;
import com.project.bloggingapp.payloads.ApiResponse;
import com.project.bloggingapp.payloads.CategoryDto;
import com.project.bloggingapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = this.categoryService.createCategory(categoryDto);
        ApiResponse response = new ApiResponse(AppConstants.CREATED_STATUS_CODE, AppConstants.CATEGORY_ADDED_SUCCESS, true, savedCategory);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer cid){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, cid);
        ApiResponse response = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.CATEGORY_UPDATE_SUCCESS, true, updatedCategory);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cid){
        this.categoryService.deleteCategory(cid);
        ApiResponse response = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.CATEGORY_DELETE_SUCCESS, true, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> getSingleCategory(@PathVariable("categoryId") Integer cid){
        CategoryDto categoryDto = this.categoryService.getCategory(cid);
        ApiResponse response = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.CATEGORY_GOT_SUCCCESS, true, categoryDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getCategories(){
        List<CategoryDto> categoryDtos = this.categoryService.getAllCategories();
        ApiResponse response = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.CATEGORY_GOT_ALL_SUCCESS, true, categoryDtos);
        return ResponseEntity.ok(response);
    }
}
