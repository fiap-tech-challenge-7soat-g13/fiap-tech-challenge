package com.fiap.challenge.tastefood.core.domain.validation;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
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

        validator.add(Validation.notBlank(product.getName(), "É obrigatório informar o nome"));
        validator.add(Validation.assertFalse(nameAlreadyExists(id, product.getName()), String.format("Já existe um produto com o nome '%s'", product.getName())));
        validator.add(Validation.notBlank(product.getDescription(), "É obrigatório informar a descrição"));
        validator.add(Validation.notBlank(product.getBrand(), "É obrigatório informar a marca"));
        validator.add(Validation.notNull(product.getCategory(), "É obrigatório informar a categoria"));
        validator.add(Validation.notNull(product.getPrice(), "É obrigatório informar o preço"));
        validator.add(Validation.greaterThan(product.getPrice(), BigDecimal.ZERO, "O preço deve ser maior que zero"));

        validator.assertEmptyMessages();
    }

    private boolean nameAlreadyExists(Long id, String name) {
        return name != null && productRepository.findByName(name).filter(Predicate.not(same(id))).isPresent();
    }

    private Predicate<Product> same(Long id) {
        return product -> product.getId().equals(id);
    }

}
