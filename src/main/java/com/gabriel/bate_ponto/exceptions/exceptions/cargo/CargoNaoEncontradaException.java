package com.gabriel.bate_ponto.exceptions.exceptions.cargo;

public class CargoNaoEncontradaException extends RuntimeException {
    public CargoNaoEncontradaException(String message) {
        super(message);
    }

    public CargoNaoEncontradaException(){
        super("Cargo não encontrada.");
    }
}
