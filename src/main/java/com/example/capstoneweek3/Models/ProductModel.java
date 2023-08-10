package com.example.capstoneweek3.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductModel {
    @NotNull(message = "id must not be null")
    @Positive()
    private Integer id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 3, message = "name must be more than 2 characters")
    private String name;

    @NotNull(message = "price must not be null")
    @Positive(message = "price must be positive value")
    private Double price;

    @NotNull(message = "category ID must not be null")
    private Integer categoryID;
}
