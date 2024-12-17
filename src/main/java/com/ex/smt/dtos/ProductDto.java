package com.ex.smt.dtos;

import com.ex.smt.entities.Categeory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productId;
    private String productName;
    private String productDesc;
    private boolean isActive;
    private String price;
    private Categeory categeory;
}
