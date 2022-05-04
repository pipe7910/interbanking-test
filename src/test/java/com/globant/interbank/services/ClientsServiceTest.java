package com.globant.interbank.services;

import com.globant.interbank.entities.Client;
import com.globant.interbank.repositories.ClientsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@DisplayName("Test class for ClientsService")
class ClientsServiceTest {

    @InjectMocks
    ClientsService clientsService;

    @Mock
    ClientsRepository clientsRepository;

    @Mock
    Client client;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);

        client = Client.builder().id("12345").fullName("Juan Esteban").surNames("Arias Henao").businessName("Globant").businessId("98765").addres("calle 34").email("juan.arias@globant.com").phone("23456").build();
    }

    @Test
    @DisplayName("Must save a client and return the client saved")
    void mustSaveAClientAndReturnTheClientSaved() {
        Mockito.when(clientsRepository.save(client)).thenReturn(client);

        Client result = clientsService.save(client);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, client);
        Mockito.verify(clientsRepository).save(client);
    }

    @Test
    @DisplayName("Must delete a client and return true when it is deleted")
    void mustSaveAClientAndReturnTrueWhenItIsDeleted() {
        client.setId("12124");
        Mockito.when(clientsRepository.findById(client.getId())).thenReturn(Optional.ofNullable(client));

        boolean result = clientsService.delete(client);

        Assertions.assertEquals(result, true);
        Mockito.verify(clientsRepository).findById(any(String.class));
    }

    @Test
    @DisplayName("Must return a client when the id exists")
    void MustReturnAClientWhenTheIdExists() {
        client.setId("12124");
        Mockito.when(clientsRepository.findById(client.getId())).thenReturn(Optional.ofNullable(client));

        Client result = clientsService.findById(client.getId());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getFullName(), client.getFullName());
        Mockito.verify(clientsRepository).findById(any(String.class));
    }

}