package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.adapter.driver.dto.ProductRequest;
import com.fiap.challenge.tastefood.adapter.driver.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.application.useCase.category.CategoryListUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductCreateUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductListUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductRemoveUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductUpdateUseCase;
import com.fiap.challenge.tastefood.core.application.vo.ProductInput;
import com.fiap.challenge.tastefood.core.application.vo.ProductOutput;
import com.fiap.challenge.tastefood.core.domain.valueObject.ProductCategory;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductCreateUseCase productCreateUseCase;
    private final ProductUpdateUseCase productUpdateUseCase;
    private final ProductRemoveUseCase productRemoveUseCase;
    private final ProductListUseCase productListUseCase;
    private final CategoryListUseCase categoryListUseCase;
    private final ProductMapper productMapper;

    @PostMapping(path = "/product")
    public void create(@RequestBody ProductRequest productRequest) {
        ProductInput productInput = productMapper.toProductInput(productRequest);
        productCreateUseCase.execute(productInput);
    }

    @PutMapping(path = "/product/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        ProductInput productInput = productMapper.toProductInput(productRequest);
        productUpdateUseCase.execute(id, productInput);
    }

    @DeleteMapping(path = "/product/{id}")
    public void remove(@PathVariable Long id) {
        productRemoveUseCase.execute(id);
    }

    @GetMapping(path = "/product")
    public ResponseEntity<?> list(@RequestParam(required = false) String category) {
        try {
            List<ProductOutput> productsOutput = productListUseCase.execute(StringUtils.isBlank(category) ? null : ProductCategory.valueOf(category.toUpperCase()));
            return ResponseEntity
                    .status(OK)
                    .body(productMapper.toProductResponse(productsOutput));
        } catch (Exception e) {
            return ResponseEntity
                    .status(NO_CONTENT)
                    .build();
        }
    }

    @GetMapping(path = "/product/category")
    public ResponseEntity<?> list() {
        return ResponseEntity
                .status(OK)
                .body(categoryListUseCase.execute());
    }

}
