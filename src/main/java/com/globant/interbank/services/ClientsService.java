package com.globant.interbank.services;

import com.globant.interbank.entities.Client;
import com.globant.interbank.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientsService {

    @Autowired
    ClientsRepository clientsRepository;

    public Client save(Client client) {
        return clientsRepository.save(client);
    }

    public boolean delete(Client client) {
        if (clientsRepository.findById(client.getId()).get() != null) {
            clientsRepository.deleteById(client.getId());
            return true;
        } else {
            new Exception("The client does not exist");
        }
        return false;
    }

    public Client findById(String id) {
        Optional<Client> client = clientsRepository.findById(id);
        if (client.get() != null) {
            return client.get();
        } else {
            new Exception("The client does not exist");
        }
        return null;
    }

}
