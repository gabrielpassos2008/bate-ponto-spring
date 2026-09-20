package com.gabriel.bate_ponto.exceptions.exceptions.ponto;

public class PontoNaoEncontradoException extends RuntimeException {
    public PontoNaoEncontradoException(String message) {
        super(message);
    }
    public PontoNaoEncontradoException(){
        super("Registros não encontrada.");
    }
}
