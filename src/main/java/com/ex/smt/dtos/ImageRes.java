package com.ex.smt.dtos;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageRes {
    private String imgName;
    private String message;
    private boolean success;
    private HttpStatus status;
}
