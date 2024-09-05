package com.example.prueba.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransaccionDtoResponse {
        private String code;
        private String message;
        private TransaccionDtoResponseData data;
}
