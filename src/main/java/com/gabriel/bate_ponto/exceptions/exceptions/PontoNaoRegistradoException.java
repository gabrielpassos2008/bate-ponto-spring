package com.gabriel.bate_ponto.exceptions.exceptions;

public class PontoNaoRegistradoException extends RuntimeException {
    public PontoNaoRegistradoException(String message) {
        super(message);
    }
    public PontoNaoRegistradoException(){
        super("Seu registro de ponto não está completo. Verifique se todas as batidas foram registradas.");
    }
}
