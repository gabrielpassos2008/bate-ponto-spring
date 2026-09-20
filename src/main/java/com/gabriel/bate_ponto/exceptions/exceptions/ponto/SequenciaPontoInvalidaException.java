package com.gabriel.bate_ponto.exceptions.exceptions.ponto;

public class SequenciaPontoInvalidaException extends RuntimeException {
    public SequenciaPontoInvalidaException(String message) {
        super(message);
    }
    public SequenciaPontoInvalidaException(){
        super("Os registros de ponto não estão na sequência correta. A sequência esperada é: Início, Intervalo, Fim do Intervalo e Saída.");
    }
}
