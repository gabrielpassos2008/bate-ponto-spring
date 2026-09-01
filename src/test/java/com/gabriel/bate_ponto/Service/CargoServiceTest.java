package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.dto.cargo.CargoCreateDTO;
import com.gabriel.bate_ponto.dto.cargo.CargoResponseDTO;
import com.gabriel.bate_ponto.exceptions.exceptions.CargoNaoEncontradaException;
import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.repository.CargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void ValidarRetornarPorId_quandoNaoExiste(){
        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CargoNaoEncontradaException.class,() -> cargoService.retornarPorId(1L));
        verify(cargoRepository).findById(1L);

    }
    @Test
    void ValidarlistarCargos_quandoExiste(){
        when(cargoRepository.findAll()).thenReturn(List.of(cargo));
        List<CargoResponseDTO> lista = cargoService.listarCargos();
        assertEquals(1,lista.size());
        verify(cargoRepository).findAll();
    }

    @Test
    void validarRegistrarCargo_deveRegistrarERetornarCargo(){
        CargoCreateDTO dto = new CargoCreateDTO("gestor");
        when(cargoRepository.save(cargo)).thenReturn(cargo);

        CargoResponseDTO resultado = cargoService.registrarCargo(dto);
        assertEquals(1L,resultado.id());
        verify(cargoRepository).save(any(Cargo.class));

    }

}
