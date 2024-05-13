package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.adapter.driver.dto.ClientFormDto;
import com.fiap.challenge.tastefood.core.application.dto.Client;
import com.fiap.challenge.tastefood.core.domain.entity.ClientEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface ClientMapper {
    
    ClientEntity toClientEntity(Client client);

    Client fromClientEntityToClient(ClientEntity clientEntity);

    Client fromClientFormDto(ClientFormDto clientFormDto);

}
