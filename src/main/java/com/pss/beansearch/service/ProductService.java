package com.pss.beansearch.service;

import com.pss.beansearch.dto.ProductDto;
import com.pss.beansearch.entity.Product;
import com.pss.beansearch.mapper.ProductMapper;
import com.pss.beansearch.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public void insertProduct(ProductDto productDto){
        System.out.println(productDto);
        Product e = productMapper.toEntity(productDto);
        e.setVersion(1);
        System.out.println(e);
        productRepository.save(e);
    }

    @Transactional
    public void updateProduct(ProductDto productDto){

        System.out.println(productDto);
        Product e = productMapper.toEntity(productDto);
        System.out.println(e);
        ///e.setVersion(e.getVersion()+1);
        productRepository.save(e);
    }

}
