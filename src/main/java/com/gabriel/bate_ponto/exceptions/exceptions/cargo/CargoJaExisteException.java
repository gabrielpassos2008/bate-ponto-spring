package com.gabriel.bate_ponto.exceptions.exceptions.cargo;

public class CargoJaExisteException extends RuntimeException {
    public CargoJaExisteException(String message) {
        super(message);
    }
    public CargoJaExisteException(){
        super("Este cargojá está cadastrado. Informe outro nome.");
    }
}
