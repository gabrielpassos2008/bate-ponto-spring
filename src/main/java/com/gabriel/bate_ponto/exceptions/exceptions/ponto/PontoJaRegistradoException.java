package com.gabriel.bate_ponto.exceptions.exceptions.ponto;

public class PontoJaRegistradoException extends RuntimeException {
    public PontoJaRegistradoException(String message) {
        super(message);
    }
    public PontoJaRegistradoException(){
        super("Você já completou todas as marcações do dia.");
    }
}
