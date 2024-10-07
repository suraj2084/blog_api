package com.suraj.blog_api.surajblogapi.Services.UserServiceImpl;

import java.util.List;

import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suraj.blog_api.surajblogapi.Entities.Category;
import com.suraj.blog_api.surajblogapi.Exceptions.ResourceNotFoundException;
import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.CategoryDto;

import com.suraj.blog_api.surajblogapi.Repository.CategoryRepo;
import com.suraj.blog_api.surajblogapi.Services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public ApiResponse createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepo.save(category);
        return new ApiResponse("Category Created successfully", true);
    }

    @Override
    public ApiResponse updateCategory(CategoryDto categoryDto, Integer id) {
        // First Finding category
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setC_title(categoryDto.getC_title());
        category.setC_description(categoryDto.getC_description());
        return new ApiResponse("Category updated successfully", true);

    }

    @Override
    public ApiResponse deleteCategory(Integer id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepo.delete(category);
        return new ApiResponse("Category Delete successfully", true);
    }

    @Override
    public ApiResponse DeleteAllCategory() {
        categoryRepo.deleteAll();
        return new ApiResponse("All Category Delete successfully", true);

    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoeryDtos = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return categoeryDtos;
    }

}
