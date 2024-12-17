package com.ex.smt.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExResponse {
    private String message;
    private boolean success;
    private HttpStatus status;
}
