package com.polijic.soa.services;

import com.polijic.soa.models.Client;
import java.util.List;
import javax.persistence.EntityNotFoundException;

public interface ClientsService {

    Client save(final Client client);

    void delete(final String id) throws EntityNotFoundException;

    List<Client> findByFilters(final String id, final String fullName, final String businessName, final String email, final String phone)
        throws EntityNotFoundException;
}
