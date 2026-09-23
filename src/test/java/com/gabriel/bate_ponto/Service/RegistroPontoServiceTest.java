package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.Service.usuarios.UsuarioService;
import com.gabriel.bate_ponto.dto.registroPonto.RegistroPontoResponse;
import com.gabriel.bate_ponto.exceptions.exceptions.ponto.PontoJaRegistradoException;
import com.gabriel.bate_ponto.model.RegistroPonto;
import com.gabriel.bate_ponto.model.TipoPonto;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.RegistroPontoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistroPontoServiceTest {

    @Mock
    private RegistroPontoRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private RegistroPontoService service;

    private RegistroPonto inicio;
    private RegistroPonto intervalo;
    private RegistroPonto fimIntervalo;
    private RegistroPonto saida;
    private Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("teste");

        inicio = new RegistroPonto();
        inicio.setId(1L);
        inicio.setData(LocalDate.now());
        inicio.setHora(LocalTime.of(8,30));
        inicio.setTipo(TipoPonto.INICIO);
        inicio.setOrigem("web");

        intervalo = new RegistroPonto();
        intervalo.setId(2L);
        intervalo.setData(LocalDate.now());
        intervalo.setHora(LocalTime.of(12,30));
        intervalo.setTipo(TipoPonto.INTERVALO);
        intervalo.setOrigem("web");

        fimIntervalo = new RegistroPonto();
        fimIntervalo.setId(3L);
        fimIntervalo.setData(LocalDate.now());
        fimIntervalo.setHora(LocalTime.of(13,30));
        fimIntervalo.setTipo(TipoPonto.FIM_INTERVALO);
        fimIntervalo.setOrigem("web");

        saida = new RegistroPonto();
        saida.setId(4L);
        saida.setData(LocalDate.now());
        saida.setHora(LocalTime.of(17,30));
        saida.setTipo(TipoPonto.FIM_INTERVALO);
        saida.setOrigem("web");
    }

    @Test
    void validarSeDiaJaFinalizado_quandoRetornaException(){
        when(repository.existsByDataAndUsuarioAndTipo(LocalDate.now(),usuario,TipoPonto.SAIDA)).thenReturn(true);
        assertThrows(PontoJaRegistradoException.class,()-> service.validarSeDiaJaFinalizado(usuario,LocalDate.now()));
        verify(repository).existsByDataAndUsuarioAndTipo(LocalDate.now(),usuario,TipoPonto.SAIDA);
    }

    @Test
    void validarSeDiaJaFinalizado_quandoNaoEsta(){
        when(repository.existsByDataAndUsuarioAndTipo(LocalDate.now(),usuario,TipoPonto.SAIDA)).thenReturn(false);
        service.validarSeDiaJaFinalizado(usuario,LocalDate.now());
        verify(repository).existsByDataAndUsuarioAndTipo(LocalDate.now(),usuario,TipoPonto.SAIDA);
    }

}
