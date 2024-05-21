package com.fiap.challenge.tastefood.core.domain.validation;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductCreateValidator {

    private ProductUpdateValidator validator;

    public void validate(Product product) {
        validator.validate(null, product);
    }

}
