package com.fiap.challenge.tastefood.core.application.validator;

import com.fiap.challenge.tastefood.core.application.vo.ProductInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductCreateValidator {

    private ProductUpdateValidator validator;

    public void validate(ProductInput productInput) {
        validator.validate(null, productInput);
    }

}
