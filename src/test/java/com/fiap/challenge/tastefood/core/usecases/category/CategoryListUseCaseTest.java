package com.fiap.challenge.tastefood.core.usecases.category;

import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryListUseCaseTest {


    private final CategoryListUseCase categoryListUseCase = new CategoryListUseCase();

    @Test
    void shouldListAllStatuses() {

        List<String> expected = Arrays.stream(ProductCategory.values()).map(ProductCategory::name).toList();

        List<String> actual = categoryListUseCase.execute();

        assertEquals(expected, actual);
    }

}