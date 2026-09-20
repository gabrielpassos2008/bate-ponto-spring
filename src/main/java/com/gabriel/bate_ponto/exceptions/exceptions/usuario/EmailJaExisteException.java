package com.gabriel.bate_ponto.exceptions.exceptions.usuario;

public class EmailJaExisteException extends RuntimeException {
    public EmailJaExisteException(String message) {
        super(message);
    }
    public EmailJaExisteException(){
        super("Este e-mail já está cadastrado. Informe outro endereço de e-mail.");
    }
}
