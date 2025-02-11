package com.polijic.soa.controllers.v1.docs;

import com.polijic.soa.dto.v1.ApiResponseDTO;
import com.polijic.soa.dto.v1.ClientDto;
import com.polijic.soa.models.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Clients")
public interface ClientControllerDocs {

    @Operation(summary = "Create a client")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Client created successfully",
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClientDto.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<ClientDto>> save(@RequestBody final Client client);

    @Operation(summary = "Delete a client")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Client deleted successfully",
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiResponseDTO.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<Object>> delete(@PathVariable final String id);

    @Operation(summary = "Update a client")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Client updated successfully",
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClientDto.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<ClientDto>> update(@RequestBody final Client client) throws EntityNotFoundException;

    @Operation(summary = "Find a client by filters")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Client list retrieved successfully",
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = ClientDto.class)))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<List<ClientDto>>> findClients(
        @RequestParam(name = "id", required = false) final String id,
        @RequestParam(name = "fullName", required = false) final String fullName,
        @RequestParam(name = "businessName", required = false) final String businessName,
        @RequestParam(name = "email", required = false) final String email,
        @RequestParam(name = "phone", required = false) final String phone
    ) throws EntityNotFoundException;
}
