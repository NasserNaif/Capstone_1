package com.example.capstoneweek3.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerStockModel {
    @NotNull(message = "ID must not be null")
    @Positive()
    private Integer id;

    @NotNull(message = "ID must not be null")
    private Integer productID;

    @NotNull(message = "ID must not be null")
    private Integer merchantID;

    @NotNull(message = "ID must not be null")
    @Min(value = 10, message = "stock must be equal or more than 10")
    private Integer stock;
}
