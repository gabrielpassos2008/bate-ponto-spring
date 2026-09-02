package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.dto.cargo.CargoCreateDTO;
import com.gabriel.bate_ponto.dto.cargo.CargoResponseDTO;
import com.gabriel.bate_ponto.dto.cargo.CargoUpdateDTO;
import com.gabriel.bate_ponto.exceptions.exceptions.CargoNaoEncontradaException;
import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public CargoResponseDTO registrarCargo(CargoCreateDTO dto){
        this.validarNomeSeExiste(dto.nome());
        Cargo cargo = new Cargo();
        cargo.setNome(dto.nome());
        cargoRepository.save(cargo);
        return new CargoResponseDTO(cargo.getId(), cargo.getNome());
    }

    public List<CargoResponseDTO> listarCargos(){
        return cargoRepository.findAll().stream()
                .map(cargo -> new CargoResponseDTO(
                        cargo.getId()
                        ,cargo.getNome()))
                .toList();
    }

    public Cargo retornarPorId(Long id){
        return cargoRepository.findById(id).orElseThrow(CargoNaoEncontradaException::new);
    }

    public CargoResponseDTO editarCargo(CargoUpdateDTO dto){
        this.validarNomeSeExiste(dto.nome());
        Cargo cargo = retornarPorId(dto.id());
        cargo.setNome(dto.nome());
        return new CargoResponseDTO(cargo.getId(), cargo.getNome());
    }

    public void validarNomeSeExiste(String nome){
        if (cargoRepository.existsByNome(nome)){
            throw new CargoNaoEncontradaException();
        }
    }
}

