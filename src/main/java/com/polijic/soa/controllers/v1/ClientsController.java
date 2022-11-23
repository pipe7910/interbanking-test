package com.polijic.soa.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polijic.soa.controllers.v1.docs.ClientControllerDocs;
import com.polijic.soa.dto.v1.ApiResponseDTO;
import com.polijic.soa.dto.v1.ClientDto;
import com.polijic.soa.models.Client;
import com.polijic.soa.services.ClientsService;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*")
public class ClientsController implements ClientControllerDocs {

    private final ClientsService clientsService;
    private final ObjectMapper objectMapper;

    public ClientsController(final ClientsService clientsService, final ObjectMapper objectMapper) {
        this.clientsService = clientsService;
        this.objectMapper = objectMapper;
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDTO<ClientDto>> save(@RequestBody final Client client) {
        final Client clientSaved = clientsService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<ClientDto>builder()
                .code(HttpStatus.CREATED.value())
                .message("Client saved successfully")
                .data(objectMapper.convertValue(clientSaved, ClientDto.class))
                .build()
            );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Object>> delete(@PathVariable final String id) throws EntityNotFoundException {
        clientsService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.builder()
                    .code(HttpStatus.OK.value())
                .message("Client deleted successfully")
                .build()
            );
    }

    @PutMapping()
    public ResponseEntity<ApiResponseDTO<ClientDto>> update(@RequestBody final Client client) {
        final Client clientUpdated = clientsService.save(client);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<ClientDto>builder()
                .code(HttpStatus.OK.value())
                .message("Client updated successfully")
                .data(objectMapper.convertValue(clientUpdated, ClientDto.class))
                .build()
            );
    }

    @GetMapping("")
    public ResponseEntity<ApiResponseDTO<List<ClientDto>>> findClients(
        @RequestParam(name = "id", required = false) final String id,
        @RequestParam(name = "fullName", required = false) final String fullName,
        @RequestParam(name = "businessName", required = false) final String businessName,
        @RequestParam(name = "email", required = false) final String email,
        @RequestParam(name = "phone", required = false) final String phone
    ) throws EntityNotFoundException {
        List<Client> clientList = clientsService.findByFilters(id, fullName, businessName, email, phone);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<List<ClientDto>>builder()
                .code(HttpStatus.OK.value())
                .message("Client list retrieved successfully")
                .data(clientList.stream().map(this::convertModelToDto).collect(Collectors.toList()))
                .build()
            );
    }

    private ClientDto convertModelToDto(Client client) {
        return objectMapper.convertValue(client, ClientDto.class);
    }

}
