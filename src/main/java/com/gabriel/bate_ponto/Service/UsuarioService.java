package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.dto.usuario.UsuarioCreateDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioResponseDTO;
import com.gabriel.bate_ponto.exceptions.exceptions.EmailJaExisteException;
import com.gabriel.bate_ponto.exceptions.exceptions.UsuarioNaoEncontradoException;
import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.model.Role;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private TokenService tokenService;

    public void validarEmailJaExiste(String email){
        if (usuarioRepository.existsByEmail(email)){
            throw new EmailJaExisteException();
        }
    }

    public UsuarioResponseDTO registrarColaborador(UsuarioCreateDTO dto){

        this.validarEmailJaExiste(dto.email());
        Cargo cargo = cargoService.retornarPorId(dto.IdCargo());

        Usuario novo = new Usuario();
        novo.setGestor(retornarUsuarioAutenticado());
        novo.setNome(dto.nome());
        novo.setEmail(dto.email());
        novo.setSenha(passwordEncoder.encode(dto.senha()));
        novo.setAtivo(dto.ativo());
        novo.setRole(Role.ROLE_COLABORADOR);
        novo.setCargo(cargo);

        this.usuarioRepository.save(novo);

        return new UsuarioResponseDTO(novo.getEmail(), novo.getNome(), novo.isAtivo(),novo.getRole());
    }

    public Usuario retornarPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public Usuario retornarUsuarioAutenticado(){
        return retornarPorEmail(tokenService.retornarEmailDoToken());
    }
}
