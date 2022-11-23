package com.polijic.soa.services.impl;

import com.polijic.soa.models.Client;
import com.polijic.soa.repositories.ClientsRepository;
import com.polijic.soa.services.ClientsService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientsServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;

    public ClientsServiceImpl(final ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public Client save(final Client client) {
        return clientsRepository.save(client);
    }

    public void delete(final String id) throws EntityNotFoundException {
        if (clientsRepository.findById(id).isPresent()) {
            clientsRepository.deleteById(id);
        }
        throw new EntityNotFoundException("The client does not exist");
    }

    public List<Client> findByFilters(
        final String id,
        final String fullName,
        final String businessName,
        final String email,
        final String phone
    ) throws EntityNotFoundException {
        return clientsRepository.findByIdAndFullNameAndBusinessNameAndEmailAndPhone(id, fullName, businessName, email, phone);
    }

}
