package com.fiap.challenge.tastefood.core.usecases.category;

import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryListUseCase {

    public List<String> execute() {
        return Arrays.stream(ProductCategory.values()).map(ProductCategory::name).toList();
    }

}