package com.pss.beansearch.mapper;

import com.pss.beansearch.dto.ProductDto;
import com.pss.beansearch.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(source = "id", target = "productId.id")
    @Mapping(source = "version", target = "productId.version")
    Product toEntity(ProductDto productDto);

    @Mapping(source = "productId.id", target = "id")
    @Mapping(source = "productId.version", target = "version")
    ProductDto toDto(Product product);
}