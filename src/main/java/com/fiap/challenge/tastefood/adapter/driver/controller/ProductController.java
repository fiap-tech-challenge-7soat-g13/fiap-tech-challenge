package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.adapter.driver.dto.ProductRequest;
import com.fiap.challenge.tastefood.core.application.dto.ProductResponse;
import com.fiap.challenge.tastefood.core.application.useCase.product.CreateProductUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ListProductsUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.RemoveProductUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.UpdateProductUseCase;
import com.fiap.challenge.tastefood.core.domain.entity.ProductCategoryEnum;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductRequestMapper;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final RemoveProductUseCase removeProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;

    @PostMapping(path = "/product")
    public void create(@RequestBody ProductRequest request) {
        createProductUseCase.execute(productRequestMapper.map(request));
    }

    @PutMapping(path = "/product/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductRequest request) {
        updateProductUseCase.execute(id, productRequestMapper.map(request));
    }

    @DeleteMapping(path = "/product/{id}")
    public void remove(@PathVariable Long id) {
        removeProductUseCase.execute(id);
    }

    @GetMapping(path = "/product")
    public List<ProductResponse> list(@RequestParam(required = false) ProductCategoryEnum category) {
        return productResponseMapper.map(listProductsUseCase.execute(category));
    }

}
