package com.example.capstoneweek3.Controllers;

import com.example.capstoneweek3.APIresponse.ApiResponse;
import com.example.capstoneweek3.Models.ProductModel;
import com.example.capstoneweek3.Services.CategoryService;
import com.example.capstoneweek3.Services.ProductService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @Getter
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ArrayList<ProductModel> getAllProduct() {
        return productService.getProducts();
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid ProductModel newProduct, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isCategoryIsValid = categoryService.checkCategory(newProduct.getCategoryID());

        if (isCategoryIsValid) {
            productService.addProduct(newProduct);
            return ResponseEntity.status(200).body(new ApiResponse("product added"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("wrong category ID,"));
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid ProductModel newProduct, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean checkCategory = categoryService.checkCategory(id);
        if (checkCategory) {
            boolean isUpdated = productService.updateProduct(id, newProduct);
            if (isUpdated) {
                return ResponseEntity.status(200).body(new ApiResponse("product added"));
            } else {
                return ResponseEntity.status(400).body(new ApiResponse("wrong product ID "));
            }
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("no Category with this ID : " + newProduct.getCategoryID()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        boolean isUpdated = productService.deleteProduct(id);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("product added"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("no product with this ID : " + id));
        }
    }

    // EXTRA
    @GetMapping("/filter/{money}")
    public ArrayList<ProductModel> filterProducts(@PathVariable Integer money) {
        return productService.filter(money);
    }

    @GetMapping("/category/{id}")
    public ArrayList<ProductModel> filterCategory(@PathVariable Integer id) {
        return productService.category(id);
    }

}
