package com.polijic.soa.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ApiResponseDTO")
public class ApiResponseDTO<T> {

    @Schema(example = "1")
    @JsonProperty(value = "code")
    protected int code;

    @Schema(example = "Example message")
    @JsonProperty(value = "message")
    protected String message;

    @Schema(example = "Example error message")
    @JsonProperty(value = "error")
    protected List<String> error;

    @JsonProperty(value = "data")
    protected T data;
}
