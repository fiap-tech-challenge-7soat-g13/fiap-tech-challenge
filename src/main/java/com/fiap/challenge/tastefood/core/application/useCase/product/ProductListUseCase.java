package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.application.dto.ProductResponse;
import com.fiap.challenge.tastefood.core.application.mapper.ProductResponseMapper;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.valueObject.ProductCategory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductListUseCase {

    private final ProductResponseMapper mapper;
    private final ProductRepository repository;

    @Transactional
    public List<ProductResponse> execute(ProductCategory category) {
        return mapper.map(category == null ? repository.findByActiveTrue() : repository.findByCategoryAndActiveTrue(category));
    }

}
