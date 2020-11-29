package com.clients.controller;

import com.clients.entity.Client;
import com.clients.service.ClientService;
import javassist.NotFoundException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v1/client")
public class ClientController implements AbstractController{
    @Autowired
    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<?> getAllClients() throws NotFoundException {

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getClientById(Long id) throws NotFoundException {
       try {
           Client response = service.getId(id);
           EntityModel<Client> resource = EntityModel.of(response);
           List<String> allowedActions = allowedActions(response);
           allowedActions.stream().forEach(action ->{
               if (action.equalsIgnoreCase("getAll")){
                   try {
                       resource.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllClients()).withRel("getAll"));
                   } catch (NotFoundException exception) {
                       exception.printStackTrace();
                   }
               }else if (action.equalsIgnoreCase("save")){
                   resource.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).saveClient(response)).withRel("save"));
               }
               if (action.equalsIgnoreCase("update")){
                   try {
                       resource.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).updateClient(id,response)).withRel("update"));
                   } catch (NotFoundException exception) {
                       exception.printStackTrace();
                   }

               } else if (action.equalsIgnoreCase("delete")){
                   try {
                       resource.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).deleteById(id)).withRel("delete"));
                   } catch (NotFoundException exception) {
                       exception.printStackTrace();
                   }

               }
           });
           //resource.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllClients()).withRel("getAll"));
           return new ResponseEntity<>(resource,HttpStatus.OK);
       }catch (NotFoundException exception){
           System.out.println("customer not found");
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
    public ResponseEntity<?> updateClient(Long id, Client client) throws NotFoundException, HttpClientErrorException.BadRequest {
        return new ResponseEntity<>(service.updateClient(id, client),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) throws NotFoundException {
        try {
            service.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(HttpServerErrorException.InternalServerError e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    protected List<String> allowedActions(Client client){
        List<String> actions = new ArrayList<>();
        if (client.getId() <= 0){
            actions.add("save");
        }else {
            actions.add("getAll");
            actions.add("getId");
            actions.add("update");
            actions.add("delete");
        }
        return actions;
    }

}
