package com.example.prueba.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClienteDtoResponse {

    private String code;
    private String message;
    private ClienteDtoResponseData data;
}
