package com.fiap.challenge.tastefood.core.domain.validation;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.entity.QProduct;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.util.StringUtils;
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
        validator.add(Validation.assertFalse(nameAlreadyExists(id, product.getName()), "Já existe um produto com o nome '%s'", product.getName()));
        validator.add(Validation.notBlank(product.getDescription(), "É obrigatório informar a descrição"));
        validator.add(Validation.notNull(product.getCategory(), "É obrigatório informar a categoria"));
        validator.add(Validation.notNull(product.getPrice(), "É obrigatório informar o preço"));
        validator.add(Validation.greaterThan(product.getPrice(), BigDecimal.ZERO, "O preço deve ser maior que zero"));
        validator.add(Validation.notEmpty(product.getImages(), "É obrigatório informar as imagens"));

        for (String image : product.getImages()) {
            validator.add(Validation.assertTrue(StringUtils.isValidUrl(image), "A imagem deve ser uma URL válida"));
        }

        validator.assertEmptyMessages();
    }

    private boolean nameAlreadyExists(Long id, String name) {
        return name != null && productRepository.findOne(QProduct.product.name.equalsIgnoreCase(name).and(QProduct.product.removed.isFalse())).filter(Predicate.not(same(id))).isPresent();
    }

    private Predicate<Product> same(Long id) {
        return product -> product.getId().equals(id);
    }

}