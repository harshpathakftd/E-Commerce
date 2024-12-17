package com.ex.smt.Service;

import org.springframework.data.domain.Page;

import com.ex.smt.dtos.ProductDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productdto);
    ProductDto updateProduct(String productId , ProductDto productDto);
    ProductDto getProductById(String productId);
    Page<ProductDto> getAllProduct(int pageNumber , int pageSize);
    void deleteProduct(String productId);
    // create product with categeory
    ProductDto crateWithCategeory(ProductDto productDto, String categeoryId);
}
