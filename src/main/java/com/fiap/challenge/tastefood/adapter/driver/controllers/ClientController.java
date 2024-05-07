package com.fiap.challenge.tastefood.adapter.driver.controllers;

import com.fiap.challenge.tastefood.adapter.driver.formsDto.ClientFormDto;
import com.fiap.challenge.tastefood.core.applications.dtos.Client;
import com.fiap.challenge.tastefood.core.applications.useCases.client.CreateClientUseCase;
import com.fiap.challenge.tastefood.core.applications.useCases.client.GetAllClientUseCase;
import com.fiap.challenge.tastefood.core.domain.mapper.ClientMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final GetAllClientUseCase getAllClientUseCase;
    public final ClientMapper clientMapper;

    @Autowired
    public ClientController(CreateClientUseCase createClientUseCase,
                            GetAllClientUseCase getAllClientUseCase,
                            ClientMapper clientMapper) {
        this.createClientUseCase = createClientUseCase;
        this.getAllClientUseCase = getAllClientUseCase;
	    this.clientMapper = clientMapper;
    }

    @PostMapping(path = "/cliente/registre")
    @Transactional
    public ResponseEntity<Long> create(@RequestBody ClientFormDto clienteFormDto) {
        Long clientId = createClientUseCase.execute(this.clientMapper.fromClientFormDto(clienteFormDto));
        if (clientId != null)
            return ResponseEntity.ok(clientId);
        else {
            log.info("Cliente já possui cadastro!");
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/client")
    @Transactional
    public ResponseEntity findAll() {
        List<Client> clients = getAllClientUseCase.execute();
        if (clients.isEmpty()) {
            log.info("Não há clientes!");
            return new ResponseEntity<>(clients, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
    }

}
