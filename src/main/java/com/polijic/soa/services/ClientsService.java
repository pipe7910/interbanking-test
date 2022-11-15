package com.polijic.soa.services;

import com.polijic.soa.models.Client;
import javax.persistence.EntityNotFoundException;

public interface ClientsService {

    Client save(Client client);

    void delete(String id) throws EntityNotFoundException;

    Client findById(String id) throws EntityNotFoundException;
}
