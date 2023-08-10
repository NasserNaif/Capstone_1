package com.example.capstoneweek3.Controllers;


import com.example.capstoneweek3.APIresponse.ApiResponse;
import com.example.capstoneweek3.Models.CategoryModel;
import com.example.capstoneweek3.Services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ArrayList<CategoryModel> getCategory() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid CategoryModel newCat, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        } else {
            categoryService.addCategory(newCat);
            return ResponseEntity.status(201).body(new ApiResponse("category added "));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid CategoryModel newCat, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        } else {
            boolean isUpdated = categoryService.updateCategory(newCat, id);
            if (isUpdated) {
                return ResponseEntity.status(201).body(new ApiResponse("category updated"));

            } else {
                return ResponseEntity.status(400).body(new ApiResponse("no category with ID : " + id));

            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("category deleted"));

        } else {
            return ResponseEntity.status(400).body(new ApiResponse("no category with ID : " + id));
        }
    }
}
