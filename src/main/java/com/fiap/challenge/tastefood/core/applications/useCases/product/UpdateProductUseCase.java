package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.CategoryEnum;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.exception.OrderException;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpdateProductUseCase {

    private final ProductRepository productRepository;

    @Autowired
    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(Long id, Product product) throws OrderException {
        Optional<ProductEntity> producto = productRepository.findById(id);;
        if (producto.isPresent())
            updateExistProduct(producto.get().getId(), product);

        throw new OrderException(String.format("Produto com id: %s não existe!", id));
    }

    private void updateExistProduct(Long id, Product product) {
        List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
        products.forEach(p -> {
            if (p.getId().equals(id)) {
                p.setDescription(product.getDescription());
                p.setBrand(product.getBrand());
                p.setCategory(CategoryEnum.valueOf(product.getCategory().name()));
                p.setValor(product.getValor());
            }
        });
    }

}
