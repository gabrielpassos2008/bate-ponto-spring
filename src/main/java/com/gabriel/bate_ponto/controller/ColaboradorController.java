package com.gabriel.bate_ponto.controller;

import com.gabriel.bate_ponto.Service.RegistroPontoService;
import com.gabriel.bate_ponto.dto.registroPonto.RegistroPontoCreate;
import com.gabriel.bate_ponto.dto.registroPonto.RegistroPontoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .body(pontoService.listarPontoPorDia());
    }
}
