package com.fiap.challenge.tastefood.core.application.useCase.client;

import com.fiap.challenge.tastefood.core.application.dto.Client;
import com.fiap.challenge.tastefood.adapter.driver.infra.ClientGateway;
import com.fiap.challenge.tastefood.core.domain.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllClientUseCase {

    private final ClientGateway clientGateway;
    public final ClientMapper clientMapper;

    public List<Client> execute() {
        return clientGateway.findAll().stream()
                .map(clientMapper::fromClientEntityToClient)
                .collect(Collectors.toList());
    }

}