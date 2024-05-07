package com.fiap.challenge.tastefood.core.applications.useCases.client;

import com.fiap.challenge.tastefood.core.applications.dtos.Client;
import com.fiap.challenge.tastefood.adapter.driven.infra.ClientGateway;
import com.fiap.challenge.tastefood.core.domain.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllClientUseCase {

    private final ClientGateway clientGateway;
    public final ClientMapper clientMapper;

    @Autowired
    public GetAllClientUseCase(ClientGateway clientGateway,
                               ClientMapper clientMapper) {
        this.clientGateway = clientGateway;
	    this.clientMapper = clientMapper;
    }

    public List<Client> execute() {
        return clientGateway.findAll().stream()
                .map(clientMapper::fromClientEntityToClient)
                .collect(Collectors.toList());
    }

}