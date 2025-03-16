package com.ecommerce.project.serviceImpl;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
     private ModelMapper modelMapper;
    @Override
    public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize) {
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize);
        Page<Category>categoryPage= categoryRepository.findAll(pageDetails);
        System.out.println("categoryPage1");
        List<Category>categoryList= categoryPage.getContent();
        System.out.println("categoryPage2");
        if(categoryList.isEmpty()){
            throw new APIException("No category created till now.");
        }
        List<CategoryDTO> categoryDTOS = categoryList.stream().
                map(category-> modelMapper.map(category, CategoryDTO.class)).toList();
        System.out.println(categoryDTOS);
        CategoryResponse categoryResponse= new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalpages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category= modelMapper.map(categoryDTO,Category.class);
      Category savedCategory=categoryRepository.findByCategoryName(category.getCategoryName());
      if(savedCategory!=null){
          throw new APIException("Category with the name " + category.getCategoryName() + " already exists !!!");
      }
        categoryRepository.save(category);
      return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(Long id,CategoryDTO categoryDTO) {
        // System.out.println("id :" + category.getCategoryId());
        Category category =modelMapper.map(categoryDTO,Category.class);
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", category.getCategoryId()));

        existingCategory.setCategoryName(category.getCategoryName());
        Category savedcategory=categoryRepository.save(existingCategory);
        return modelMapper.map(savedcategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long id) {
      Category category=categoryRepository.findById(id).orElseThrow(()->
                 new ResourceNotFoundException("Category","categoryId",id));
        categoryRepository.delete(category);
      return modelMapper.map(category,CategoryDTO.class);

    }

}
