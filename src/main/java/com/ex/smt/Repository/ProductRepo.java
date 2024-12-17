package com.ex.smt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.smt.entities.Product;

public interface ProductRepo extends JpaRepository<Product , String>{
    
}
