package com.globant.interbank.repositories;

import com.globant.interbank.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends CrudRepository<Client, String> {
}
