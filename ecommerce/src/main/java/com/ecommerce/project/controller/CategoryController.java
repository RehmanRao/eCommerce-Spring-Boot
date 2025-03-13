package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import com.ecommerce.project.serviceImpl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category>categoryList= categoryService.getAllCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
    @PostMapping("/admin/category")
    public  ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
        System.out.println("category Service"+category);
         categoryService.createCategory(category);
         return new ResponseEntity<>("category created ", HttpStatus.CREATED);
    }
    @PutMapping("/admin/category/{id}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category , @PathVariable Long id){
     Category updateCategory= categoryService.updateCategory(id,category);
     return new ResponseEntity<>("updated category",HttpStatus.OK);
    }

    @DeleteMapping("/admin/category/{id}")

    public ResponseEntity<String> deleteMapping(@PathVariable Long id) {
       String  message= categoryService.deleteCategory(id);
       return  new ResponseEntity<>(message,HttpStatus.OK);
    }


}
