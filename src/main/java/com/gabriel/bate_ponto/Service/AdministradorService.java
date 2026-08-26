package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.dto.cargo.CargoResponseDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioCreateDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioResponseDTO;
import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CargoService cargoService;

    public UsuarioResponseDTO registrarUsuario(UsuarioCreateDTO dto){
        this.usuarioService.validarEmailJaExiste(dto.email());
        Cargo cargo = cargoService.retornarPorId(dto.IdCargo());

        Usuario novo = new Usuario();
        novo.setNome(dto.nome());
        novo.setEmail(dto.email());
        novo.setSenha(passwordEncoder.encode(dto.senha()));
        novo.setAtivo(dto.ativo());
        novo.setRole(dto.role());
        novo.setCargo(cargo);

        this.usuarioRepository.save(novo);

        return new UsuarioResponseDTO(novo.getEmail(), novo.getNome(), novo.isAtivo(),novo.getRole());
    }
}
