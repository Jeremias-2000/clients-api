package com.clients.service;

import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface AbstractService<T> {
    List<T> getAll();
    T getId(Long id) throws NotFoundException;
    T saveClient(T client);
    T updateClient(Long id,T client) throws NotFoundException;
    void deleteClient(Long id) throws NotFoundException;
}
