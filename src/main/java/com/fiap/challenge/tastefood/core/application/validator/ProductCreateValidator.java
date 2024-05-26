package com.fiap.challenge.tastefood.core.application.validator;

import com.fiap.challenge.tastefood.core.application.dto.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductCreateValidator {

    private ProductUpdateValidator validator;

    public void validate(ProductRequest product) {
        validator.validate(null, product);
    }

}
