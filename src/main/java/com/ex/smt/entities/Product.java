package com.ex.smt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product{
    @Id
    private String productId;
    private String productName;
    private String productDesc;
    private boolean isActive;
    private String price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categeory_id")
    private Categeory categeory;
}
