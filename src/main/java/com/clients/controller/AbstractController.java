package com.clients.controller;

import com.clients.entity.Client;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface AbstractController {

    @GetMapping(value = "/getAll",produces= "application/json")
    ResponseEntity<?> getAllClients() throws NotFoundException;

    @GetMapping(value = "/search/{id}" ,produces= "application/json")
    ResponseEntity<?> getClientById(@PathVariable("id") Long id) throws NotFoundException;

    @PostMapping("/save")
    ResponseEntity<?> saveClient(@Valid @RequestBody Client client);

    @PutMapping("/update/{id}")
    ResponseEntity<?> updateClient(@PathVariable("id") Long id,@RequestBody Client client) throws NotFoundException;

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws NotFoundException;


}
