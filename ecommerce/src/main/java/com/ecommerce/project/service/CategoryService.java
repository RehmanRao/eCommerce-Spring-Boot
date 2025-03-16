package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService{
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize);

public CategoryDTO createCategory(CategoryDTO categoryDTO);

public  CategoryDTO updateCategory(Long id,CategoryDTO categoryDTO);

public  CategoryDTO  deleteCategory(Long id);



}
