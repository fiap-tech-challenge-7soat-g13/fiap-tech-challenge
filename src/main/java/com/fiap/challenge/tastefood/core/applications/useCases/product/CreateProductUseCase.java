package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.exception.InvalidDataException;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateProductUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public Long execute(Product product) {

        validate(product);

        ProductEntity entity = this.productMapper.toProductEntity(product);

        entity = productRepository.save(entity);

        return entity.getId();
    }

    private void validate(Product product) {

        List<String> messages = new ArrayList<>();

        if (StringUtils.isBlank(product.getDescription())) {
            messages.add("É obrigatório informar a descrição");
        } else if (productRepository.findByDescription(product.getDescription()).isPresent()) {
            messages.add(String.format("Já existe um produto com a descrição '%s'", product.getDescription()));
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

}
