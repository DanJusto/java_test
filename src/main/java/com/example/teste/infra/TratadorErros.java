package com.example.teste.infra;

import com.example.teste.domain.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErros {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErro(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
