package com.clients.service;

import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AbstractService<T> {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getId(Long id) throws NotFoundException;
    ResponseEntity<?> saveClient(T client);
    ResponseEntity<?> updateClient(Long id,T client) throws NotFoundException;
    ResponseEntity<?> deleteClient(Long id) throws NotFoundException;
}
