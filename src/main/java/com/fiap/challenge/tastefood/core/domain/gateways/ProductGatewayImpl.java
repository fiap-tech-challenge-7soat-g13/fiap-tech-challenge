package com.fiap.challenge.tastefood.core.domain.gateways;

import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductGatewayImpl {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductGatewayImpl(ProductRepository productRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
	    this.productMapper = productMapper;
    }

    public List<Product> findAll() {
        List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
        return products.stream().map(this.productMapper::fromProductEntity).toList();
    }

    public Optional<Product> findById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        return product.isPresent() ? product.map(this.productMapper::fromProductEntity) : Optional.empty();
    }

}
