package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService{
List<Category> getAllCategories();

public  void createCategory(Category category);

public  Category updateCategory(Long id,Category category);

public  String  deleteCategory(Long id);



}
