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
        if (validCPF(client.getCpf()) == true){
            System.out.println("Saved customer !");
            return repository.save(client);

        }else {
            System.out.println("unable to save customer");
            return null;
        }



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

    protected boolean validCPF(String cpf){

        int iDigito1Aux = 0, iDigito2Aux = 0, iDigitoCPF;
        int iDigito1 = 0, iDigito2 = 0, iRestoDivisao = 0;
        String strDigitoVerificador, strDigitoResultado;

        if (!cpf.substring(0,1).equals("")){
            try{
                cpf = cpf.replace('.',' ');
                cpf = cpf.replace('-',' ');
                cpf = cpf.replaceAll(" ","");
                for (int iCont = 1; iCont < cpf.length() -1; iCont++) {
                    iDigitoCPF = Integer.valueOf(cpf.substring(iCont -1, iCont)).intValue();
                    iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
                    iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
                }
                iRestoDivisao = (iDigito1Aux % 11);
                if (iRestoDivisao < 2) {
                    iDigito1 = 0;
                } else {
                    iDigito1 = 11 - iRestoDivisao;
                }
                iDigito2Aux += 2 * iDigito1;
                iRestoDivisao = (iDigito2Aux % 11);
                if (iRestoDivisao < 2) {
                    iDigito2 = 0;
                } else {
                    iDigito2 = 11 - iRestoDivisao;
                }
                strDigitoVerificador = cpf.substring(cpf.length()-2, cpf.length());
                strDigitoResultado = String.valueOf(iDigito1) + String.valueOf(iDigito2);
                strDigitoVerificador.equals(strDigitoResultado);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

}
