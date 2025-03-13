package com.ecommerce.project.serviceImpl;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        List<Category>categoryList= categoryRepository.findAll();
        if(categoryList.isEmpty()){
            throw new APIException("No category created till now.");
        }
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
      Category savedCategory=categoryRepository.findByCategoryName(category.getCategoryName());
      if(savedCategory!=null){
          throw new APIException("Category with the name " + category.getCategoryName() + " already exists !!!");
      }
        categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id,Category category) {
        // System.out.println("id :" + category.getCategoryId());
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", category.getCategoryId()));

        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);
    }



    @Override
    public String deleteCategory(Long id) {
      Category category=categoryRepository.findById(id).orElseThrow(()->
                 new ResourceNotFoundException("Category","categoryId",id));
      categoryRepository.delete(category);
      return " deleted ";

    }

}
