package com.polijic.soa.repositories;

import com.polijic.soa.models.Client;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends CrudRepository<Client, String> {

    @Query("SELECT c FROM Client c WHERE "
        + "(:id is null or c.id = :id) "
        + " AND (:fullName is null or c.fullName = :fullName) "
        + " AND (:businessName is null or c.businessName = :businessName) "
        + " AND (:email is null or c.email = :email) "
        + " AND (:phone is null or c.phone = :phone) ")
    List<Client> findByIdAndFullNameAndBusinessNameAndEmailAndPhone(
        final String id,
        final String fullName,
        final String businessName,
        final String email,
        final String phone
    );
}
