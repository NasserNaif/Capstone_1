package com.example.capstoneweek3.Controllers;


import com.example.capstoneweek3.APIresponse.ApiResponse;
import com.example.capstoneweek3.Models.MerStockModel;
import com.example.capstoneweek3.Services.MerStockService;
import com.example.capstoneweek3.Services.MerchantService;
import com.example.capstoneweek3.Services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class MerStockController {

    private final MerStockService merStockService;
    private final MerchantService merchantService;
    private final ProductService productService;


    @GetMapping("/get")
    public ArrayList<MerStockModel> getAllStocks() {
        return merStockService.getStocks();
    }

    @PostMapping("/add")
    public ResponseEntity addNewStock(@RequestBody @Valid MerStockModel newStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (merchantService.checkMerchant(newStock.getMerchantID()) && productService.checkProduct(newStock.getProductID())) {
            merStockService.addStock(newStock);
            return ResponseEntity.status(201).body(new ApiResponse("stock added"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("product ID or merchant ID is wrong"));
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStock(@PathVariable Integer id, @RequestBody @Valid MerStockModel newStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        if (merchantService.checkMerchant(newStock.getMerchantID()) && productService.checkProduct(newStock.getProductID())) {
            boolean isUpdated = merStockService.updateStock(id, newStock);

            if (isUpdated) {
                return ResponseEntity.status(201).body(new ApiResponse("stock updated"));
            } else {
                return ResponseEntity.status(400).body(new ApiResponse("wrong stock ID"));
            }
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("product ID or merchant ID is wrong in new merchant stock you added"));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStock(@PathVariable Integer id) {
        boolean isDeleted = merStockService.deleteStock(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("stock updated"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("wrong stock ID"));
        }
    }
}
