package com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity productEntity);

    void update(Product product, @MappingTarget Product productSave);

    List<Product> toProduct(List<ProductEntity> productEntities);

}
