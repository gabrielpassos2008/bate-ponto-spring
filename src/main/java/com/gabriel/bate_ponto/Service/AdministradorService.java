package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.dto.administrador.AdminCreateDTO;
import com.gabriel.bate_ponto.dto.administrador.AdminResponseDTO;
import com.gabriel.bate_ponto.exceptions.exceptions.UsuarioNaoEncontradoException;
import com.gabriel.bate_ponto.model.Role;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AdminResponseDTO registrarAdmin(AdminCreateDTO dto){
        this.usuarioService.validarEmailJaExiste(dto.email());

        Usuario novo = new Usuario();
        novo.setNome(dto.nome());
        novo.setEmail(dto.email());
        novo.setSenha(passwordEncoder.encode(dto.senha()));
        novo.setAtivo(true);
        novo.setRole(Role.ROLE_ADMIN);
        novo.setCargo(null);

        this.usuarioRepository.save(novo);

        return new AdminResponseDTO(novo.getNome(), novo.getEmail(), dto.senha());
    }

    public List<AdminResponseDTO> listarUsuario (){
        return usuarioRepository.findByRole(Role.ROLE_ADMIN).orElseThrow(UsuarioNaoEncontradoException::new)
                .stream()
                .map(usuario -> new AdminResponseDTO(usuario.getNome(), usuario.getEmail(), usuario.getSenha()))
                .toList();
    }
}
