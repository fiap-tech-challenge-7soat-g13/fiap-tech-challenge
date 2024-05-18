package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.adapter.driver.dto.ProductRequest;
import com.fiap.challenge.tastefood.core.application.dto.ProductResponse;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductCreateUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductListUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductRemoveUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductUpdateUseCase;
import com.fiap.challenge.tastefood.core.domain.entity.ProductCategoryEnum;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductRequestMapper;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductCreateUseCase productCreateUseCase;
    private final ProductUpdateUseCase productUpdateUseCase;
    private final ProductRemoveUseCase productRemoveUseCase;
    private final ProductListUseCase productListUseCase;
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;

    @PostMapping(path = "/product")
    public void create(@RequestBody ProductRequest request) {
        productCreateUseCase.execute(productRequestMapper.map(request));
    }

    @PutMapping(path = "/product/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductRequest request) {
        productUpdateUseCase.execute(id, productRequestMapper.map(request));
    }

    @DeleteMapping(path = "/product/{id}")
    public void remove(@PathVariable Long id) {
        productRemoveUseCase.execute(id);
    }

    @GetMapping(path = "/product")
    public List<ProductResponse> list(@RequestParam(required = false) ProductCategoryEnum category) {
        return productResponseMapper.map(productListUseCase.execute(category));
    }

}
