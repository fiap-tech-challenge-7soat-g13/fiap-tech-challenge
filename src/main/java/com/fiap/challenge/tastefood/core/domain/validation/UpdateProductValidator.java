package com.fiap.challenge.tastefood.core.domain.validation;

import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class UpdateProductValidator {

    private ProductRepository productRepository;

    public void validate(Long id, Product product) {

        Validator validator = new Validator();

        validator.add(Validation.notBlank(product.getDescription(), "É obrigatório informar a descrição"));
        validator.add(Validation.assertFalse(descriptionAlreadyExists(id, product.getDescription()), String.format("Já existe um produto com a descrição '%s'", product.getDescription())));
        validator.add(Validation.notBlank(product.getBrand(), "É obrigatório informar a marca"));
        validator.add(Validation.notNull(product.getCategory(), "É obrigatório informar a categoria"));
        validator.add(Validation.notNull(product.getValue(), "É obrigatório informar o valor"));
        validator.add(Validation.greaterThan(product.getValue(), BigDecimal.ZERO, "O valor deve ser maior que zero"));

        validator.assertEmptyMessages();
    }

    private boolean descriptionAlreadyExists(Long id, String description) {
        return description != null && productRepository.findByDescription(description).filter(Predicate.not(same(id))).isPresent();
    }

    private Predicate<ProductEntity> same(Long id) {
        return product -> product.getId().equals(id);
    }

}
