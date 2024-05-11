package com.fiap.challenge.tastefood.core.domain.gateways;

import com.fiap.challenge.tastefood.adapter.driven.infra.ClientGateway;
import com.fiap.challenge.tastefood.core.domain.entities.ClientEntity;
import com.fiap.challenge.tastefood.core.domain.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class ClientGatewayImpl implements ClientGateway {

    private final ClientRepository clientRepository;

    public Long create(ClientEntity client) {
        ClientEntity save = clientRepository.save(client);
        log.info("Cliente registrado com sucesso!");
        return save.getId();
    }

    public List<ClientEntity> findAll() {
        return (List<ClientEntity>) clientRepository.findAll();
    }

    public Optional<ClientEntity> findByDocument(String document) {
        return clientRepository.findByDocument(document);
    }

}
