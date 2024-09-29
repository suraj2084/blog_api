package com.suraj.blog_api.surajblogapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.blog_api.surajblogapi.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
