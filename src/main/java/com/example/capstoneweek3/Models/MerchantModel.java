package com.example.capstoneweek3.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantModel {
    @NotNull(message = "ID must not be null")
    @Positive(message = "ID must be positive")
    private Integer id;

    @NotEmpty(message = "name must not be null")
    @Size(min = 3, message = "merchant name must be equal or more than 3 characters")
    private String name;
}
