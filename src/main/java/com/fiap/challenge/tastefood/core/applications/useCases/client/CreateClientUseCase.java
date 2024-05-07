package com.fiap.challenge.tastefood.core.applications.useCases.client;

import com.fiap.challenge.tastefood.core.applications.dtos.Client;
import com.fiap.challenge.tastefood.core.domain.entities.ClientEntity;
import com.fiap.challenge.tastefood.adapter.driven.infra.ClientGateway;
import com.fiap.challenge.tastefood.core.domain.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CreateClientUseCase {

    private final ClientGateway clientGateway;
    public ClientMapper clientMapper;

    @Autowired
    public CreateClientUseCase(ClientGateway clientGateway,
                               ClientMapper clientMapper) {
        this.clientGateway = clientGateway;
        this.clientMapper = clientMapper;
    }

    public Long execute(Client client) {
        Optional<ClientEntity> optionalClient = clientGateway.findByDocument(client.getDocument());
        if (!optionalClient.isPresent()) {
            return clientGateway.create(this.clientMapper.toClientEntity(client));
        }
        log.info("Cliente já possui cadastro!");
        return null;
    }
}