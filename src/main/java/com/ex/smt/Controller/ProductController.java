package com.ex.smt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ex.smt.Service.CategeoryService;
import com.ex.smt.Service.ProductService;
import com.ex.smt.dtos.CategeoryDto;
import com.ex.smt.dtos.ProductDto;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategeoryService categeoryService;

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto){
        ProductDto product = productService.createProduct(productDto);
        return new ResponseEntity<>(product , HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto, @PathVariable String productId){
        ProductDto product = productService.updateProduct(productId, productDto);
        return new ResponseEntity<>(product , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAll(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "0") int pageSize
    ){
        Page<ProductDto> allProduct = productService.getAllProduct(pageNumber, pageSize);
        return new ResponseEntity<>(allProduct , HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProdutById(@PathVariable String productId){
        ProductDto product = productService.getProductById(productId);
        return new ResponseEntity<>(product , HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable String categeoryId){
        productService.deleteProduct(categeoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{categeory}/products")
    public ResponseEntity<ProductDto> craeteProductWitgCategeory(@PathVariable String categeoryId, @RequestBody ProductDto productDto){
        ProductDto crateWithCategeory = productService.crateWithCategeory(productDto, categeoryId);
        return new ResponseEntity<>(crateWithCategeory , HttpStatus.CREATED);
    }
}
