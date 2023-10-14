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
        long highestVersion = productRepository.getMaxVersion(productDto.id());
        System.out.println("highestVersion "+highestVersion);
        try {
            Thread.sleep(productDto.delay()*1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        if(e.getVersion() == highestVersion){
            e.setVersion(productDto.version()+1);
            productRepository.save(e);
        }

    }

}
