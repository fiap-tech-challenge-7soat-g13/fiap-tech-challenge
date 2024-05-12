package com.fiap.challenge.tastefood.adapter.driver.controllers;

import com.fiap.challenge.tastefood.adapter.driver.formsDto.ProductFormDto;
import com.fiap.challenge.tastefood.core.applications.dtos.CategoryEnum;
import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.applications.useCases.product.CreateProductUseCase;
import com.fiap.challenge.tastefood.core.applications.useCases.product.ListProductsUseCase;
import com.fiap.challenge.tastefood.core.applications.useCases.product.RemoveProductUseCase;
import com.fiap.challenge.tastefood.core.applications.useCases.product.UpdateProductUseCase;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
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
    private final ProductMapper productMapper;

    @PostMapping(path = "/product")
    public void create(@RequestBody ProductFormDto productFormDto) {
        createProductUseCase.execute(this.productMapper.fromOrderFormDto(productFormDto));
    }

    @PutMapping(path = "/product/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductFormDto productFormDto) {
        updateProductUseCase.execute(id, this.productMapper.fromOrderFormDto(productFormDto));
    }

    @DeleteMapping(path = "/product/{id}")
    public void remove(@PathVariable Long id) {
        removeProductUseCase.execute(id);
    }

    @GetMapping(path = "/product")
    public List<Product> list(@RequestParam(required = false) CategoryEnum category) {
        return listProductsUseCase.execute(category);
    }

}
