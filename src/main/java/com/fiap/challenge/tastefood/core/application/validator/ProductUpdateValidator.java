package com.fiap.challenge.tastefood.core.application.validator;

import com.fiap.challenge.tastefood.core.application.util.validation.Validation;
import com.fiap.challenge.tastefood.core.application.util.validation.Validator;
import com.fiap.challenge.tastefood.core.application.vo.ProductInput;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class ProductUpdateValidator {

    private ProductRepository productRepository;

    public void validate(Long id, ProductInput productInput) {

        Validator validator = new Validator();

        validator.add(Validation.notBlank(productInput.getName(), "É obrigatório informar o nome"));
        validator.add(Validation.assertFalse(nameAlreadyExists(id, productInput.getName()), "Já existe um produto com o nome '%s'", productInput.getName()));
        validator.add(Validation.notBlank(productInput.getDescription(), "É obrigatório informar a descrição"));
        validator.add(Validation.notNull(productInput.getCategory(), "É obrigatório informar a categoria"));
        validator.add(Validation.notNull(productInput.getPrice(), "É obrigatório informar o preço"));
        validator.add(Validation.greaterThan(productInput.getPrice(), BigDecimal.ZERO, "O preço deve ser maior que zero"));
        validator.add(Validation.notEmpty(productInput.getImages(), "É obrigatório informar as imagens"));

        if (productInput.getImages() != null) {
            for (String image : productInput.getImages()) {
                validator.add(Validation.notInvalidUrl(image, "A imagem deve ser uma URL válida"));
            }
        }

        validator.assertEmptyMessages();
    }

    private boolean nameAlreadyExists(Long id, String name) {
        return name != null && productRepository.findByNameAndActiveTrue(name).filter(Predicate.not(same(id))).isPresent();
    }

    private Predicate<Product> same(Long id) {
        return product -> product.getId().equals(id);
    }

}
