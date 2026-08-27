package com.gabriel.bate_ponto.controller;

import com.gabriel.bate_ponto.Service.CargoService;
import com.gabriel.bate_ponto.dto.cargo.CargoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ponto/gestor")
public class GestorController {
    @Autowired
    private CargoService cargoService;

    @GetMapping("/gestor/listar/cargo")
    public ResponseEntity<List<CargoResponseDTO>> getListarCargo(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cargoService.listarCargos());
    }
}
