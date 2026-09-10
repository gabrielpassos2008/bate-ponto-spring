package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.Service.usuarios.UsuarioService;
import com.gabriel.bate_ponto.dto.registroPonto.RegistroPontoResponse;
import com.gabriel.bate_ponto.model.RegistroPonto;
import com.gabriel.bate_ponto.repository.RegistroPontoRepository;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class RegistroPontoService {

    @Autowired
    private RegistroPontoRepository pontoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public RegistroPontoResponse registrarPonto(){

        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.now();

        RegistroPonto ponto = new RegistroPonto();
        ponto.setData(data);
        ponto.setHora(hora);
        ponto.setOrigem("Web");
        ponto.setTipo("entrada");
        ponto.setUsuario(usuarioService.retornarUsuarioAutenticado());
        pontoRepository.save(ponto);
        return new RegistroPontoResponse(
                ponto.getData(),
                ponto.getHora(),
                ponto.getTipo());
    }

}
