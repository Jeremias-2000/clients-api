package com.clients.service;

import com.clients.entity.Client;

import com.clients.repository.ClientRepository;
import javassist.NotFoundException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService implements AbstractService<Client>{


    @Autowired
    private ClientRepository repository;


    @Override
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Client> getId(Long id) throws NotFoundException {
        try {
            Client search = repository.findById(id).orElseThrow(()
                    ->new NotFoundException("Id inválido: " + id));

            return new ResponseEntity<>(search,HttpStatus.OK);
        }catch (NotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<Client> saveClient(@Valid @RequestBody Client client) {
        try {
            return new ResponseEntity<>(repository.save(client),HttpStatus.CREATED);
        }catch (DataException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Client> updateClient(Long id,@Valid @RequestBody Client client) throws NotFoundException {
        Client search = repository.findById(id).orElseThrow(()
                ->new NotFoundException("Id inválido: " + id));
        try {
            search.setName(client.getName());
            search.setBirth(client.getBirth());
            search.setGender(client.getGender());
            search.setEmail(client.getEmail());
            search.setContact(client.getContact());
            search.setAddress(client.getAddress());
            search.setCpf(client.getCpf());
            return new ResponseEntity<>(search,HttpStatus.OK);
        }catch (BadRequestException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> deleteClient(Long id) throws NotFoundException {
        try {
            Client search = repository.findById(id).orElseThrow(()
                    ->new NotFoundException("Id inválido: " + id));
            repository.delete(search);
            return new ResponseEntity<>(search,HttpStatus.OK);
        }catch(InternalServerErrorException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
