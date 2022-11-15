package com.polijic.soa.controllers.v1;

import com.polijic.soa.controllers.v1.docs.AuthControllerDocs;
import com.polijic.soa.dto.v1.ApiResponseDTO;
import com.polijic.soa.dto.v1.JwtDto;
import com.polijic.soa.utils.ClientAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs {

    @GetMapping()
    public ResponseEntity<ApiResponseDTO<JwtDto>> getAuthorization() {
        String token = ClientAuthentication.generateToken();
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<JwtDto>builder()
                .code(HttpStatus.CREATED.value())
                .message("Token created successfully")
                .data(new JwtDto(token))
                .build());
    }
}
