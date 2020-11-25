package com.clients.controller;

import com.clients.entity.Client;
import com.clients.service.ClientService;
import javassist.NotFoundException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;


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
        try {
            return new ResponseEntity<>(service.getId(id),HttpStatus.OK);
        }catch (NotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> saveClient(Client client) {
        try {
            return new ResponseEntity<>(service.saveClient(client),HttpStatus.CREATED);
        }catch (DataException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> updateClient(Long id, Client client) throws NotFoundException {
        try {
            return new ResponseEntity<>(service.updateClient(id, client),HttpStatus.OK);
        }catch (BadRequestException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) throws NotFoundException {
        try {
            service.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(InternalServerErrorException e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
