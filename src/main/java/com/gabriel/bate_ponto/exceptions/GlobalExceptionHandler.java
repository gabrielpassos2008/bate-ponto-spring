package com.gabriel.bate_ponto.exceptions;

import com.gabriel.bate_ponto.dto.exception.MensagemErroDTO;
import com.gabriel.bate_ponto.exceptions.exceptions.EmailJaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailJaExisteException.class)
    public ResponseEntity<MensagemErroDTO> emailJaExiste(EmailJaExisteException exception){
        MensagemErroDTO erro = new MensagemErroDTO(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                HttpStatus.CONFLICT.name());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erro);
    }
}
