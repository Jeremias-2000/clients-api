package com.clients.controller;

import com.clients.entity.Client;
import com.clients.service.ClientService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/client")
public class ClientController implements AbstractController{
    @Autowired
    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<?> getAllClients() {

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getClientById(Long id) throws NotFoundException {
        return new ResponseEntity<>(service.getId(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveClient(Client client) {
        return service.saveClient(client);
    }

    @Override
    public ResponseEntity<?> updateClient(Long id, Client client) throws NotFoundException {
        return service.updateClient(id, client);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) throws NotFoundException {
        return service.deleteClient(id);
    }


}
