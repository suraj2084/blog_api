package com.suraj.blog_api.surajblogapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.CategoryDto;
import com.suraj.blog_api.surajblogapi.Services.CategoryService;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.createCategory(categoryDto);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> UpdateCategopry(@Valid @PathVariable("id") Integer id,
            @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<CategoryDto> categoryDtos = categoryService.getAllCategory();
        if (categoryDtos.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("No Data Available", false), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);

    }

    @GetMapping("/{c_id}")
    public CategoryDto getMethodName(@Valid @PathVariable("c_id") Integer c_id) {
        CategoryDto categoryDto = categoryService.getCategoryById(c_id);
        return categoryDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteByid(@PathVariable("id") Integer id) {
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/")
    public ResponseEntity<ApiResponse> deleteAll() {
        ApiResponse apiResponse = categoryService.DeleteAllCategory();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.ACCEPTED);
    }

}
