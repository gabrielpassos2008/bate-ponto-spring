package com.gabriel.bate_ponto.exceptions.exceptions.usuario;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
    public UsuarioNaoEncontradoException(){
        super("Usuário não encontrado");
    }
}
