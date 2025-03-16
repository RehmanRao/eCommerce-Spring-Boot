package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
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

    // done
    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name="pageNumber")Integer pageNumber,
            @RequestParam(name="pageSize")Integer pageSize
    ){
        System.out.println("get Method");
        CategoryResponse categoryList= categoryService.getAllCategories(pageNumber,pageSize);
        System.out.println("get the data");
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
    @PostMapping("/admin/category")
    public  ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        System.out.println("category Service"+categoryDTO);
         CategoryDTO SavedCategory =categoryService.createCategory(categoryDTO);
         return new ResponseEntity<>("category created ", HttpStatus.CREATED);
    }
    @PutMapping("/admin/category/{id}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO , @PathVariable Long id){
     CategoryDTO updateCategory= categoryService.updateCategory(id,categoryDTO);
     return new ResponseEntity<>("updated category",HttpStatus.OK);
    }

    @DeleteMapping("/admin/category/{id}")

    public ResponseEntity<String> deleteMapping(@PathVariable Long id) {
       CategoryDTO deleteCategory= categoryService.deleteCategory(id);
       return  new ResponseEntity<>("deleted Category",HttpStatus.OK);
    }


}
