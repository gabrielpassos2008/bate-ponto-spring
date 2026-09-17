package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.model.RegistroPonto;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.RegistroPontoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

@ExtendWith(MockitoExtension.class)
public class RegistroPontoServiceTest {

    @Mock
    private RegistroPontoRepository repository;

    @InjectMocks
    private RegistroPontoService service;

    @BeforeEach
    void setUp(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("teste");

        RegistroPonto inicio = new RegistroPonto();
        inicio.setId(1L);
        inicio.setData(LocalDate.now());
        inicio.setHora(LocalTime.of(8,30));
        inicio.setTipo("Início");
        inicio.setOrigem("web");

        RegistroPonto intervalo = new RegistroPonto();
        intervalo.setId(2L);
        intervalo.setData(LocalDate.now());
        intervalo.setHora(LocalTime.of(12,30));
        intervalo.setTipo("Intervalo");
        intervalo.setOrigem("web");

        RegistroPonto fimIntervalo = new RegistroPonto();
        fimIntervalo.setId(3L);
        fimIntervalo.setData(LocalDate.now());
        fimIntervalo.setHora(LocalTime.of(13,30));
        fimIntervalo.setTipo("Fim Intervalo");
        fimIntervalo.setOrigem("web");

        RegistroPonto saida = new RegistroPonto();
        saida.setId(4L);
        saida.setData(LocalDate.now());
        saida.setHora(LocalTime.of(17,30));
        saida.setTipo("Saída");
        saida.setOrigem("web");
    }
}
