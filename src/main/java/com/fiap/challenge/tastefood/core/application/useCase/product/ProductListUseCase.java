package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.entity.ProductCategoryEnum;
import com.fiap.challenge.tastefood.core.domain.entity.QProduct;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductListUseCase {

    private final ProductRepository productRepository;

    @Transactional
    public List<Product> execute(ProductCategoryEnum category) {

        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(QProduct.product.removed.isFalse());

        if (category != null) {
            predicate.and(QProduct.product.category.eq(category));
        }

        return Streamable.of(productRepository.findAll(predicate)).toList();
    }

}
