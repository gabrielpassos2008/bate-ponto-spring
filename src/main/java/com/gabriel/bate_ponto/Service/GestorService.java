package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.dto.usuario.UsuarioCreateDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioResponseDTO;
import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.model.Role;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GestorService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO registraGestor(UsuarioCreateDTO dto){

        this.usuarioService.validarEmailJaExiste(dto.email());
        Cargo cargo = cargoService.retornarPorId(dto.IdCargo());

        Usuario novo = new Usuario();

        novo.setNome(dto.nome());
        novo.setEmail(dto.email());
        novo.setAtivo(dto.ativo());

        novo.setSenha(passwordEncoder.encode(dto.senha()));
        novo.setRole(Role.ROLE_GESTOR);

        novo.setGestor(usuarioService.retornarUsuarioAutenticado());
        novo.setCargo(cargo);

        this.usuarioRepository.save(novo);

        return new UsuarioResponseDTO(novo.getEmail(), novo.getNome(), novo.isAtivo(),novo.getRole());
    }

}
