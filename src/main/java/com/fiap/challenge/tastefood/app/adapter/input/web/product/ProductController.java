package com.fiap.challenge.tastefood.app.adapter.input.web.product;

import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.ProductRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.mapper.ProductRequestMapper;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.mapper.ProductResponseMapper;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import com.fiap.challenge.tastefood.core.useCases.category.CategoryListUseCase;
import com.fiap.challenge.tastefood.core.useCases.product.ProductCreateUseCase;
import com.fiap.challenge.tastefood.core.useCases.product.ProductListUseCase;
import com.fiap.challenge.tastefood.core.useCases.product.ProductRemoveUseCase;
import com.fiap.challenge.tastefood.core.useCases.product.ProductUpdateUseCase;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductCreateUseCase productCreateUseCase;
    private final ProductUpdateUseCase productUpdateUseCase;
    private final ProductRemoveUseCase productRemoveUseCase;
    private final ProductListUseCase productListUseCase;
    private final CategoryListUseCase categoryListUseCase;
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;

    @PostMapping(path = "/product")
    public ResponseEntity<?> create(@RequestBody ProductRequest productRequest) {
        Product product = productRequestMapper.toProduct(productRequest);
        Product productSaved = productCreateUseCase.execute(product);
        return ResponseEntity
                .status(CREATED)
                .body(productResponseMapper.toProductResponse(productSaved));
    }

    @PutMapping(path = "/product/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        Product product = productRequestMapper.toProduct(productRequest);
        Product productSaved = productUpdateUseCase.execute(id, product);
        return ResponseEntity
                .status(OK)
                .body(productResponseMapper.toProductResponse(productSaved));
    }

    @DeleteMapping(path = "/product/{id}")
    public void remove(@PathVariable Long id) {
        productRemoveUseCase.execute(id);
    }

    @GetMapping(path = "/product")
    public ResponseEntity<?> list(@RequestParam(required = false) String category) {
        try {
            List<Product> products = productListUseCase.execute(StringUtils.isBlank(category) ? null : ProductCategory.valueOf(category.toUpperCase()));
            return ResponseEntity
                    .status(OK)
                    .body(productResponseMapper.toProductResponse(products));
        } catch (Exception e) {
            return ResponseEntity
                    .status(NO_CONTENT)
                    .build();
        }
    }

    @GetMapping(path = "/product/category")
    public ResponseEntity<List<String>> list() {
        return ResponseEntity
                .status(OK)
                .body(categoryListUseCase.execute());
    }

}
