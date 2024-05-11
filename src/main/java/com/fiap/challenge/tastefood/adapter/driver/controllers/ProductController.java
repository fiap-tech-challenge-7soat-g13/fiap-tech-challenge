package com.fiap.challenge.tastefood.adapter.driver.controllers;

import com.fiap.challenge.tastefood.adapter.driver.formsDto.ProductFormDto;
import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.applications.useCases.product.*;
import com.fiap.challenge.tastefood.core.domain.exception.OrderException;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final RemoveProductUseCase removeProductUseCase;
    private final GetProductsByCategoryUseCase getProductsByCategoryUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final ProductMapper productMapper;

    @PostMapping(path = "/product/create")
    @Transactional
    public ResponseEntity<HttpStatus> create(@RequestBody ProductFormDto productFormDto) {
        try {
            createProductUseCase.execute(this.productMapper.fromOrderFormDto(productFormDto));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping(path = "/product/edit/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody ProductFormDto productFormDto) {
        try {
            updateProductUseCase.execute(id, this.productMapper.fromOrderFormDto(productFormDto));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "/product")
    @Transactional
    public ResponseEntity<?> findAll() {
        List<Product> products = getAllProductsUseCase.execute();
        if (products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }


    @GetMapping(path = "/product/category/{category}")
    @Transactional
    public ResponseEntity<?> findByCategory(@PathVariable String category) {
        List<Product> products = getProductsByCategoryUseCase.execute(category);
        if (products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }


    @DeleteMapping(path = "/product/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
	    try {
		    removeProductUseCase.execute(id);
            return ResponseEntity.status(HttpStatus.OK).build();
	    } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    }
    }

}
