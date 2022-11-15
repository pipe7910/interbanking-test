package com.polijic.soa.controllers.v1.docs;

import com.polijic.soa.dto.v1.ApiResponseDTO;
import com.polijic.soa.dto.v1.JwtDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth")
public interface AuthControllerDocs {

    @Operation(summary = "Create a token")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Token created successfully",
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = JwtDto.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<JwtDto>> getAuthorization();
}
