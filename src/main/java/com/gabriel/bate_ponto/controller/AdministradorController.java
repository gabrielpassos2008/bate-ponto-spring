package com.gabriel.bate_ponto.controller;

import com.gabriel.bate_ponto.Service.AdministradorService;
import com.gabriel.bate_ponto.Service.CargoService;
import com.gabriel.bate_ponto.Service.UsuarioService;
import com.gabriel.bate_ponto.dto.administrador.AdminCreateDTO;
import com.gabriel.bate_ponto.dto.administrador.AdminResponseDTO;
import com.gabriel.bate_ponto.dto.cargo.CargoCreateDTO;
import com.gabriel.bate_ponto.dto.cargo.CargoResponseDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioCreateDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioResponseDTO;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ponto/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private CargoService cargoService;

    @PostMapping("/registrar/adm")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AdminResponseDTO> postRegistrarAdm(@RequestBody AdminCreateDTO dto){
        AdminResponseDTO usuario = administradorService.registrarUsuario(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuario);
    }

    @PostMapping("/registrar/cargo")
    public ResponseEntity<CargoResponseDTO> postCriarCargo(@RequestBody CargoCreateDTO dto){
        CargoResponseDTO cargo = cargoService.registrarCargo(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cargo);
    }

    @GetMapping("/listar/adm")
    public ResponseEntity<List<AdminResponseDTO>> getListarAdm(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(administradorService.listarUsuario());
    }


}
