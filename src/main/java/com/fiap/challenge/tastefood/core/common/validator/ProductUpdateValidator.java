package com.fiap.challenge.tastefood.core.common.validator;

import com.fiap.challenge.tastefood.core.common.util.validation.Validation;
import com.fiap.challenge.tastefood.core.common.util.validation.Validator;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class ProductUpdateValidator {

    private final ProductGateway productGateway;

    public void validate(Long id, Product product) {

        Validator validator = new Validator();

        validator.add(Validation.notBlank(product.getName(), "É obrigatório informar o nome"));
        validator.add(Validation.assertFalse(nameAlreadyExists(id, product.getName()), "Já existe um produto com o nome '%s'", product.getName()));
        validator.add(Validation.notBlank(product.getDescription(), "É obrigatório informar a descrição"));
        validator.add(Validation.notNull(product.getCategory(), "É obrigatório informar a categoria"));
        validator.add(Validation.notNull(product.getPrice(), "É obrigatório informar o preço"));
        validator.add(Validation.greaterThan(product.getPrice(), BigDecimal.ZERO, "O preço deve ser maior que zero"));
        validator.add(Validation.notEmpty(product.getImages(), "É obrigatório informar as imagens"));

        if (product.getImages() != null) {
            for (String image : product.getImages()) {
                validator.add(Validation.notInvalidUrl(image, "A imagem deve ser uma URL válida"));
            }
        }

        validator.assertEmptyMessages();
    }

    private boolean nameAlreadyExists(Long id, String name) {
        return name != null && productGateway.findByNameAndActiveTrue(name).filter(Predicate.not(same(id))).isPresent();
    }

    private Predicate<Product> same(Long id) {
        return product -> product.getId().equals(id);
    }

}
