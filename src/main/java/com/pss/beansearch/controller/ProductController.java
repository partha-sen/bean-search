package com.pss.beansearch.controller;

import com.pss.beansearch.dto.ProductDto;
import com.pss.beansearch.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/re-domain/")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/v1/orgs", produces = MediaType.APPLICATION_JSON_VALUE)
    public void insert(@RequestBody ProductDto productDto){
        productService.insertProduct(productDto);
    }
    @PutMapping(value = "/v1/orgs", produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody ProductDto productDto){
        productService.updateProduct(productDto);
    }


}
