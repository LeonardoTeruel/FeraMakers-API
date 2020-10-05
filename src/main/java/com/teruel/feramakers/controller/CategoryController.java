package com.teruel.feramakers.controller;

import com.teruel.feramakers.dto.CategoryDTO;
import com.teruel.feramakers.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private  CategoryService categoryService;


    @PostMapping ("/post")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.save(categoryDTO));
    }

    @GetMapping ("/get")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getAllCategories());
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long categoryId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getCategory(categoryId));
    }


}
