package com.ex.smt.Service.Implementation;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ex.smt.Config.AppConfig;
import com.ex.smt.Exception.ResourceNotFoundException;
import com.ex.smt.Repository.CategeoryRepo;
import com.ex.smt.Repository.ProductRepo;
import com.ex.smt.Service.ProductService;
import com.ex.smt.dtos.ProductDto;
import com.ex.smt.entities.Categeory;
import com.ex.smt.entities.Product;

@Service
public class ProductImpl implements ProductService{
    @Autowired
    private ProductRepo productrepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategeoryRepo categeoryRepo;

    @Override
    public ProductDto createProduct(ProductDto productdto) {
        String productId = UUID.randomUUID().toString();
        productdto.setProductId(productId);
        Product product = mapper.map(productdto, Product.class);
        Product save = productrepo.save(product);
        return mapper.map(save, ProductDto.class);        
    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto productDto) {
        Product product = productrepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConfig.notFoudMessage));
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setProductDesc(productDto.getProductDesc());
        product.setActive(productDto.isActive());
        Product save = productrepo.save(product);
        return mapper.map(save, ProductDto.class);
    }

    @Override
    public ProductDto getProductById(String productId) {
        Product product = productrepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConfig.notFoudMessage));
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public Page<ProductDto> getAllProduct(int pageNumber, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> all = productrepo.findAll(pageable);
        Page<ProductDto> productRecords = all.map(product-> mapper.map(product, ProductDto.class));
        return productRecords;
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productrepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConfig.notFoudMessage));
        productrepo.delete(product);
    }



    @Override
    public ProductDto crateWithCategeory(ProductDto productDto, String categeoryId) {
        Categeory categeory = categeoryRepo.findById(categeoryId).orElseThrow(()-> new ResourceNotFoundException(AppConfig.notFoudMessage));
        String productId = UUID.randomUUID().toString();
        productDto.setProductId(productId);
        Product product = mapper.map(productId, Product.class);
        product.setCategeory(categeory);
        Product save = productrepo.save(product);
        return mapper.map(save, ProductDto.class);
    }
    
}
