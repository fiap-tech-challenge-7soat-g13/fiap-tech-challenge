package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.adapter.driver.formsDto.ClientFormDto;
import com.fiap.challenge.tastefood.core.applications.dtos.Client;
import com.fiap.challenge.tastefood.core.domain.entities.ClientEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T01:38:28-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21 (Azul Systems, Inc.)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientEntity toClientEntity(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        return clientEntity;
    }

    @Override
    public Client fromClientEntityToClient(ClientEntity clientEntity) {
        if ( clientEntity == null ) {
            return null;
        }

        Client client = new Client();

        return client;
    }

    @Override
    public Client fromClientFormDto(ClientFormDto clientFormDto) {
        if ( clientFormDto == null ) {
            return null;
        }

        Client client = new Client();

        return client;
    }
}
