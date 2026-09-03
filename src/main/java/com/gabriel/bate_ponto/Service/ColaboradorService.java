package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.dto.usuario.UsuarioCreateDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioResponseDTO;
import com.gabriel.bate_ponto.exceptions.exceptions.UsuarioNaoEncontradoException;
import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.model.Role;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO registrarColaborador(UsuarioCreateDTO dto){

        this.usuarioService.validarEmailJaExiste(dto.email());
        Cargo cargo = cargoService.retornarPorId(dto.IdCargo());

        Usuario novo = new Usuario();

        novo.setNome(dto.nome());
        novo.setEmail(dto.email());
        novo.setAtivo(dto.ativo());

        novo.setSenha(passwordEncoder.encode(dto.senha()));
        novo.setRole(Role.ROLE_COLABORADOR);

        novo.setGestor(usuarioService.retornarUsuarioAutenticado());
        novo.setCargo(cargo);

        this.usuarioRepository.save(novo);

        return new UsuarioResponseDTO(novo.getEmail(), novo.getNome(), novo.isAtivo(),novo.getRole(),novo.getCargo());
    }

    public List<UsuarioResponseDTO> listaDeUsuarioPeloGestor(){
        Usuario usuario = usuarioService.retornarUsuarioAutenticado();
        return usuarioRepository.findByRoleAndGestor(Role.ROLE_COLABORADOR,usuario).orElseThrow(UsuarioNaoEncontradoException::new)
                .stream().map(Usuario -> new UsuarioResponseDTO(usuario.getEmail(), usuario.getNome(), usuario.isAtivo(), usuario.getRole(),usuario.getCargo())).toList();
    }
}
