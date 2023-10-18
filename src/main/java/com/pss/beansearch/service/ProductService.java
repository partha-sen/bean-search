package com.pss.beansearch.service;

import com.pss.beansearch.dto.ProductDto;
import com.pss.beansearch.entity.Product;
import com.pss.beansearch.mapper.ProductMapper;
import com.pss.beansearch.repository.ProductRepository;
import com.pss.beansearch.sequence.ProductIdGenerator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    ProductIdGenerator seqGenerator;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ProductIdGenerator seqGenerator) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.seqGenerator = seqGenerator;
    }

    public void insertProduct(ProductDto productDto){
        System.out.println(productDto);
        Product e = productMapper.toEntity(productDto);
        System.out.println(e);
        long start = System.currentTimeMillis();
        e.getProductId().setId(seqGenerator.getValue());
        e.getProductId().setVersion(1);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(e);
        productRepository.save(e);
    }

    @Transactional
    public void updateProduct(ProductDto productDto){

        System.out.println(productDto);
        Product e = productMapper.toEntity(productDto);
        System.out.println(e);
        long currentVersion = e.getProductId().getVersion();
        e.getProductId().setVersion(currentVersion+1);
        productRepository.save(e);
    }

}
