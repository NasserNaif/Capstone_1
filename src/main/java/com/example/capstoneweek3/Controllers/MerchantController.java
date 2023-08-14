package com.example.capstoneweek3.Controllers;

import com.example.capstoneweek3.APIresponse.ApiResponse;
import com.example.capstoneweek3.Models.MerStockModel;
import com.example.capstoneweek3.Models.MerchantModel;
import com.example.capstoneweek3.Services.MerStockService;
import com.example.capstoneweek3.Services.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ArrayList getAllMerchant() {
        return merchantService.getAllMerchants();
    }

    @PostMapping("/add")
    public ResponseEntity addNewStock(@RequestBody @Valid MerchantModel newMerchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        merchantService.addMerchant(newMerchant);
        return ResponseEntity.status(201).body(new ApiResponse("merchant added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStock(@PathVariable Integer id, @RequestBody @Valid MerchantModel newMerchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = merchantService.updateMerchant(id, newMerchant);

        if (isUpdated) {
            return ResponseEntity.status(201).body(new ApiResponse("merchant updated"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("wrong merchant ID"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStock(@PathVariable Integer id) {
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("merchant updated"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("wrong merchant ID"));
        }
    }

    // Q 11
    @PutMapping("/add-stock/{productid}/{merchantid}/{amount}")
    public ResponseEntity addMoreStock(@PathVariable Integer productid, @PathVariable Integer merchantid, @PathVariable Integer amount) {
        boolean isAdded = merchantService.addMoreStock(productid, merchantid, amount);

        if (isAdded) {
            return ResponseEntity.status(201).body(new ApiResponse("amount added"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("sorry! check product ID or merchant ID"));
        }
    }
}
