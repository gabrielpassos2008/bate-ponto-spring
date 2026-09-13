package com.gabriel.bate_ponto.controller;

import com.gabriel.bate_ponto.Service.RegistroPontoService;
import com.gabriel.bate_ponto.dto.pesquisa.PesquisaRegistroPontoDTO;
import com.gabriel.bate_ponto.dto.registroPonto.RegistroPontoCreate;
import com.gabriel.bate_ponto.dto.registroPonto.RegistroPontoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ponto/gestor")
public class ColaboradorController {

    @Autowired
    private RegistroPontoService pontoService;

    @PostMapping("/bater")
    public ResponseEntity<RegistroPontoResponse> postBaterPonto(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pontoService.registrarPonto());
    }
    @GetMapping("/listar/registros")
    public ResponseEntity<List<RegistroPontoResponse>> getListarPontos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pontoService.listarPontoPorDiaDeHoje());
    }
    @GetMapping("/pesquisar/registros")
    public ResponseEntity<List<RegistroPontoResponse>> getPesquisarPontos(@RequestBody @Valid PesquisaRegistroPontoDTO dto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pontoService.listarPontoPorDiaDaPesquisa(dto));
    }
}
