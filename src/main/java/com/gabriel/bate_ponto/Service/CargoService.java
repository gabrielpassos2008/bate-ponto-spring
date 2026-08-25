package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.dto.cargo.CargoCreateDTO;
import com.gabriel.bate_ponto.dto.cargo.CargoResponseDTO;
import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public CargoResponseDTO registrarCargo(CargoCreateDTO dto){
        Cargo cargo = new Cargo();
        cargo.setNome(dto.nome());
        cargoRepository.save(cargo);
        return new CargoResponseDTO(cargo.getId(), cargo.getNome());
    }

}
