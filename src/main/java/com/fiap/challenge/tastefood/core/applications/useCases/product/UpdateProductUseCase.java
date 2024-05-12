package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.CategoryEnum;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.exception.InvalidDataException;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class UpdateProductUseCase {

    private final ProductRepository productRepository;

    @Transactional
    public void execute(Long id, Product product) throws InvalidDataException {

        validate(id, product);

        ProductEntity entity = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        entity.setDescription(product.getDescription());
        entity.setBrand(product.getBrand());
        entity.setCategory(CategoryEnum.valueOf(product.getCategory().name()));
        entity.setValue(product.getValue());

        productRepository.save(entity);
    }

    private void validate(Long id, Product product) {

        List<String> messages = new ArrayList<>();

        if (StringUtils.isBlank(product.getDescription())) {
            messages.add("É obrigatório informar a descrição");
        } else if (productRepository.findByDescription(product.getDescription()).filter(Predicate.not(same(id))).isPresent()) {
            throw new InvalidDataException(String.format("Já existe um produto com a descrição '%s'", product.getDescription()));
        }

        if (StringUtils.isBlank(product.getBrand())) {
            messages.add("É obrigatório informar a marca");
        }

        if (product.getCategory() == null) {
            messages.add("É obrigatório informar a categoria");
        }

        if (product.getValue() == null) {
            messages.add("É obrigatório informar o valor");
        } else if (product.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            messages.add("O valor deve ser maior que zero");
        }

        if (!messages.isEmpty()) {
            throw new InvalidDataException(messages);
        }
    }

    private Predicate<ProductEntity> same(Long id) {
        return entity -> entity.getId().equals(id);
    }

}
