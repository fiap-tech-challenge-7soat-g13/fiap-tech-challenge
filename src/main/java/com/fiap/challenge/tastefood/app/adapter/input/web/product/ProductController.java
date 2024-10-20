package com.fiap.challenge.tastefood.app.adapter.input.web.product;

import com.fiap.challenge.tastefood.app.adapter.input.web.product.dto.ProductRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.dto.ProductResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.mapper.ProductRequestMapper;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.mapper.ProductResponseMapper;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import com.fiap.challenge.tastefood.core.usecases.category.CategoryListUseCase;
import com.fiap.challenge.tastefood.core.usecases.product.ProductCreateUseCase;
import com.fiap.challenge.tastefood.core.usecases.product.ProductListUseCase;
import com.fiap.challenge.tastefood.core.usecases.product.ProductRemoveUseCase;
import com.fiap.challenge.tastefood.core.usecases.product.ProductUpdateUseCase;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody ProductRequest productRequest) {
        Product product = productRequestMapper.toProduct(productRequest);
        Product productSaved = productCreateUseCase.execute(product);
        return productResponseMapper.toProductResponse(productSaved);
    }

    @PutMapping(path = "/product/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        Product product = productRequestMapper.toProduct(productRequest);
        product.setId(id);
        Product productSaved = productUpdateUseCase.execute(product);
        return productResponseMapper.toProductResponse(productSaved);
    }

    @DeleteMapping(path = "/product/{id}")
    public void remove(@PathVariable Long id) {
        productRemoveUseCase.execute(id);
    }

    @GetMapping(path = "/product")
    public List<ProductResponse> list(@RequestParam(required = false) String category) {
        List<Product> products = productListUseCase.execute(StringUtils.isBlank(category) ? null : ProductCategory.valueOf(category.toUpperCase()));
        return productResponseMapper.toProductResponse(products);
    }

    @GetMapping(path = "/product/category")
    public List<String> list() {
        return categoryListUseCase.execute();
    }

}
