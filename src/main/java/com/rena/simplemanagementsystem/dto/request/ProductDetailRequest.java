package com.rena.simplemanagementsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailRequest {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String category;
    @NotNull
    private String price;
    private int quantity;
}
