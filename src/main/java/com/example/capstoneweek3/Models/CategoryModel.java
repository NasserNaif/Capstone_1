package com.example.capstoneweek3.Models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryModel {
    @NotNull(message = "category ID mustn't be null")
    @Positive(message = "ID must be positive number")
    private Integer id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 3, message = "name must be more than 2 characters")
    private String name;

}
