package com.globant.interbank.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.interbank.entities.Client;
import com.globant.interbank.services.ClientsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Test class for ClientController")
@WebMvcTest(ClientsController.class)
class ClientsControllerTest {

    private MockMvc mvc;

    @InjectMocks
    ClientsController clientsController;

    @MockBean
    ClientsService clientsService;

    @Mock
    Client client;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(clientsController).build();

        client = Client.builder().id("12345").fullName("Juan Esteban").surNames("Arias Henao").businessName("Globant").businessId("98765").addres("calle 34").email("juan.arias@globant.com").phone("23456").build();
    }

    @Test
    @DisplayName("Must save a client and return status created when the save is success")
    void mustSaveAClientAndReturnStatusCreatedWhenTheSaveIsSuccess() throws Exception {
        Mockito.when(clientsService.save(client)).thenReturn(client);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(client));

        mvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    @DisplayName("Must delete a client and return status ok when the deleted is success")
    void mustDeleteClientAndReturnStatusOKWhenTheDeletedIsSuccess() throws Exception {
        client.setId("1234");

        Mockito.when(clientsService.delete(client)).thenReturn(true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(client));

        mvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Must update a client and return status ok when the update is success")
    void mustUpdateAClientAndReturnStatusOkWhenTheUpdateIsSuccess() throws Exception {
        Mockito.when(clientsService.save(client)).thenReturn(client);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(client));

        mvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    @DisplayName("Must return a client when the id exists")
    void MustReturnAClientWhenTheIdExists() throws Exception {
        Mockito.when(clientsService.findById(client.getId())).thenReturn(client);

        mvc.perform(MockMvcRequestBuilders
                        .get("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
}