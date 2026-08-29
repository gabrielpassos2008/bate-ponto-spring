package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.repository.CargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CargoServiceTest {

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private CargoService cargoService;

    private Cargo cargo;

    @BeforeEach
    void setUp(){
        cargo = new Cargo();
        cargo.setId(1L);
        cargo.setNome("gestor");
    }

    @Test
    void ValidarRetornarPorId_quandoExiste(){
        when(cargoRepository.findById(1L))
                .thenReturn(Optional.of(cargo));

        Cargo resultado = cargoService.retornarPorId(1L);
        assertEquals(cargo,resultado);

        verify(cargoRepository).findById(1L);
    }
}
