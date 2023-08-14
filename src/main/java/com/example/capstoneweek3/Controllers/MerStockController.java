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


    @GetMapping("/get")
    public ArrayList<MerStockModel> getAllStocks() {
        return merStockService.getStocks();
    }

    @PostMapping("/add")
    public ResponseEntity addNewStock(@RequestBody @Valid MerStockModel newStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        ApiResponse result = merStockService.addStock(newStock);

        if (result.getMessage().equals("stock added")) {
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.status(400).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStock(@PathVariable Integer id, @RequestBody @Valid MerStockModel newStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        ApiResponse result = merStockService.updateStock(id, newStock);


        if (result.getMessage().equals("stock updated")) {
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.status(400).body(result);
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
