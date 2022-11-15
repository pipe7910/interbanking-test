package com.polijic.soa.repositories;

import com.polijic.soa.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends CrudRepository<Client, String> {
}
