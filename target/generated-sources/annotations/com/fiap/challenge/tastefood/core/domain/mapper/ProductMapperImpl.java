package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.adapter.driver.formsDto.ProductFormDto;
import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T01:38:29-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21 (Azul Systems, Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductEntity toProductEntity(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        return productEntity;
    }

    @Override
    public Product fromProductEntity(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        Product product = new Product();

        return product;
    }

    @Override
    public Product fromOrderFormDto(ProductFormDto productFormDto) {
        if ( productFormDto == null ) {
            return null;
        }

        Product product = new Product();

        return product;
    }
}
