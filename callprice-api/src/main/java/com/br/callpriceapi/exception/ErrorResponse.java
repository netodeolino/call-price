package com.br.callpriceapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Integer code;
    private String message;
}
