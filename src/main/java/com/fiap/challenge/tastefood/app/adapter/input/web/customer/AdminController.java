package com.fiap.challenge.tastefood.app.adapter.input.web.customer;

import com.fiap.challenge.tastefood.app.adapter.input.web.customer.dto.CustomerRequest;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.useCases.customer.CustomerAdminLoginUseCase;
import com.fiap.challenge.tastefood.core.useCases.customer.CustomerCreateAdminUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class AdminController {

    private final CustomerAdminLoginUseCase customerAdminLoginUseCase;
    private final CustomerCreateAdminUseCase customerCreateAdminUseCase;

    @PostMapping(path = "/admin/login")
    public ResponseEntity<?> login(@RequestBody CustomerRequest customerRequest) {
        Customer customerSave = customerAdminLoginUseCase.execute(customerRequest.getEmail(), customerRequest.getPassword());
        return ResponseEntity
                .status(OK)
                .body(customerSave);
    }

    @PostMapping(path = "/admin/save")
    public ResponseEntity<?> create(@RequestBody CustomerRequest customerRequest) {
        Customer customerSave = customerCreateAdminUseCase.execute(customerRequest.getEmail(), customerRequest.getPassword());
        return ResponseEntity
                .status(CREATED)
                .body(customerSave);
    }

}
