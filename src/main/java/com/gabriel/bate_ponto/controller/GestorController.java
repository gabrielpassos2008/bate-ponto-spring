package com.gabriel.bate_ponto.controller;

import com.gabriel.bate_ponto.Service.CargoService;
import com.gabriel.bate_ponto.Service.ColaboradorService;
import com.gabriel.bate_ponto.Service.UsuarioService;
import com.gabriel.bate_ponto.dto.administrador.AdminCreateDTO;
import com.gabriel.bate_ponto.dto.administrador.AdminResponseDTO;
import com.gabriel.bate_ponto.dto.cargo.CargoResponseDTO;
import com.gabriel.bate_ponto.dto.pesquisa.PesquisaUsuarioDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioCreateDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ponto/gestor")
public class GestorController {
    @Autowired
    private CargoService cargoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping("/gestor/listar/cargo")
    public ResponseEntity<List<CargoResponseDTO>> getListarCargo(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cargoService.listarCargos());
    }
    @GetMapping("/listar/colaborador")
    public ResponseEntity<List<UsuarioResponseDTO>> getListarColaborador(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(colaboradorService.listaDeUsuarioPeloGestor());
    }
    @GetMapping("/pesquisar/usuario")
    public ResponseEntity<List<UsuarioResponseDTO>> getPesquisaUsuario(@RequestBody PesquisaUsuarioDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.pesquisarUsuario(dto));
    }

    @PostMapping("/registrar/colaborador")
    public ResponseEntity<UsuarioResponseDTO> postRegistrarAdm(@RequestBody UsuarioCreateDTO dto){
        UsuarioResponseDTO usuario = colaboradorService.registrarColaborador(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuario);
    }
}
