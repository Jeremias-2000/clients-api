package com.clients.service;


import com.clients.entity.Client;

import com.clients.repository.ClientRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ClientService implements AbstractService<Client>{


    @Autowired
    private ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Client> getAll() {

        return repository.findAll();

    }

    @Override
    public Client getId(Long id) throws NotFoundException {
        try {
            return searchId(id);
        }catch (NotFoundException exception){
            return null;
        }

    }

    @Override
    public Client saveClient( Client client) {
        System.out.println("Saved customer !");
        return repository.save(client);

    }

    @Override
    public Client updateClient(Long id, Client client) throws NotFoundException {

        Client search = searchId(id);
        search.setName(client.getName());
        search.setBirth(client.getBirth());
        search.setGender(client.getGender());
        search.setEmail(client.getEmail());
        search.setContact(client.getContact());
        search.setAddress(client.getAddress());
        search.setCpf(client.getCpf());
        System.out.println("Updated customer !");
        return search;

    }

    @Override
    public void deleteClient(Long id) throws NotFoundException {
        Client search = searchId(id);
        repository.delete(search);
        System.out.println("Deleted customer !");

    }
    public Client searchId(Long id) throws NotFoundException {
        Client search = repository.findById(id).orElseThrow(()
                ->new NotFoundException("invalid id: " + id));
        return search;
    }



}
