package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.application.mapper.ProductOutputMapper;
import com.fiap.challenge.tastefood.core.application.vo.ProductOutput;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.valueObject.ProductCategory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductListUseCase {

    private final ProductOutputMapper mapper;
    private final ProductRepository repository;

    @Transactional
    public List<ProductOutput> execute(ProductCategory category) {
        return mapper.map(category == null ? repository.findByActiveTrue() : repository.findByCategoryAndActiveTrue(category));
    }

}
