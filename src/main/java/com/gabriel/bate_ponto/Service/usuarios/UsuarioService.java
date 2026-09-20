package com.gabriel.bate_ponto.Service.usuarios;

import com.gabriel.bate_ponto.Service.CargoService;
import com.gabriel.bate_ponto.Service.TokenService;
import com.gabriel.bate_ponto.dto.pesquisa.PesquisaUsuarioDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioResponseDTO;
import com.gabriel.bate_ponto.exceptions.exceptions.usuario.EmailJaExisteException;
import com.gabriel.bate_ponto.exceptions.exceptions.usuario.UsuarioNaoEncontradoException;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Usuario retornarPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public Usuario retornarUsuarioAutenticado(){
        return retornarPorEmail(tokenService.retornarEmailDoToken());
    }

    public List<UsuarioResponseDTO> pesquisarUsuario(PesquisaUsuarioDTO dto){
        return usuarioRepository.findByGestorAndCargoAndNomeContainingIgnoreCase(
                this.retornarUsuarioAutenticado(),
                cargoService.retornarPorId(dto.idCargo()),
                dto.pesquisa())
                .orElseThrow(UsuarioNaoEncontradoException::new)
                .stream().map(usuario -> new UsuarioResponseDTO(usuario.getEmail(), usuario.getNome(), usuario.isAtivo(), usuario.getRole(), usuario.getCargo()))
                .toList();
    }
}
