package com.suraj.blog_api.surajblogapi.Services;

import org.springframework.stereotype.Service;

import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.CategoryDto;
import java.util.List;

@Service
public interface CategoryService {

    ApiResponse createCategory(CategoryDto categoryDto);

    ApiResponse updateCategory(CategoryDto categoryDto, int id);

    ApiResponse deleteCategory(int id);

    ApiResponse DeleteAllCategory();

    CategoryDto getCategoryById(int id);

    List<CategoryDto> getAllCategory();
}
