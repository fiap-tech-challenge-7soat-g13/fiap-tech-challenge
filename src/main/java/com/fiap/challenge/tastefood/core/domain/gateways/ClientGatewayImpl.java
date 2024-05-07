package com.fiap.challenge.tastefood.core.domain.gateways;

import com.fiap.challenge.tastefood.adapter.driven.infra.ClientGateway;
import com.fiap.challenge.tastefood.core.domain.entities.ClientEntity;
import com.fiap.challenge.tastefood.core.domain.mapper.ClientMapper;
import com.fiap.challenge.tastefood.core.domain.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ClientGatewayImpl implements ClientGateway {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientGatewayImpl(ClientRepository clientRepository,
                             ClientMapper clientMapper){
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public Long create(ClientEntity client) {
        ClientEntity save = clientRepository.save(client);
        log.info("Cliente registrado com sucesso!");

        return save.getId();
    }

    public List<ClientEntity> findAll() {
        List<ClientEntity> clients = (List<ClientEntity>) clientRepository.findAll();
        return clients != null ? clients : List.of();
    }

    public Optional<ClientEntity> findByDocument(String document) {
        Optional<ClientEntity> client = clientRepository.findByDocument(document);

        return client.isPresent() ? client : Optional.empty();
    }

}
