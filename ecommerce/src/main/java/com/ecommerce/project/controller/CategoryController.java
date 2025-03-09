package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/api/public")
public class CategoryController {
    List<Category>categories = new ArrayList<>();
    @GetMapping("/api/public/categories")
    public List<Category> getAllCategories(){
        return categories;
    }
    @PostMapping("/api/admin/category")
    public  String createCategory(@RequestBody Category category){
        categories.add(category);
        return "added";
    }

//    @DeleteMapping("/api/admin/category/{categoryId}")
//    public String deleteCategory()


}
