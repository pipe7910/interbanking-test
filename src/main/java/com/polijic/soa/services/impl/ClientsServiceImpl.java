package com.polijic.soa.services.impl;

import com.polijic.soa.models.Client;
import com.polijic.soa.repositories.ClientsRepository;
import com.polijic.soa.services.ClientsService;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientsServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;

    public ClientsServiceImpl(final ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public Client save(Client client) {
        return clientsRepository.save(client);
    }

    public void delete(String id) throws EntityNotFoundException {
        if (clientsRepository.findById(id).isPresent()) {
            clientsRepository.deleteById(id);
        }
        throw new EntityNotFoundException("The client does not exist");
    }

    public Client findById(String id) throws EntityNotFoundException {
        return clientsRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("The client does not exist"));
    }

}
