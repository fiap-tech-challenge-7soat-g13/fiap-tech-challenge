package com.fiap.challenge.tastefood.core.application.useCase.category;

import com.fiap.challenge.tastefood.core.domain.valueObject.ProductCategory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryListUseCase {

    @Transactional
    public List<String> execute() {
        return Arrays.stream(ProductCategory.values()).map(ProductCategory::name).toList();
    }

}