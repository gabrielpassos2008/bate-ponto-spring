package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.exceptions.exceptions.EmailJaExisteException;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarEmailJaExiste(String email){
        if (usuarioRepository.existsByEmail(email)){
            throw new EmailJaExisteException();
        }
    }
}
