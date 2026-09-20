package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.Service.usuarios.UsuarioService;
import com.gabriel.bate_ponto.dto.pesquisa.PesquisaUsuarioDTO;
import com.gabriel.bate_ponto.dto.usuario.UsuarioResponseDTO;
import com.gabriel.bate_ponto.exceptions.exceptions.usuario.EmailJaExisteException;
import com.gabriel.bate_ponto.exceptions.exceptions.usuario.UsuarioNaoEncontradoException;
import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    private Usuario gestor;

    private Cargo cargo;

    @BeforeEach
    void setUp(){
        gestor = new Usuario();
        gestor.setNome("gestor");

        cargo = new Cargo();
        cargo.setId(1L);
        cargo.setNome("cargo");

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("teste");
        usuario.setEmail("teste@gmail.com");
        usuario.setSenha("teste");
        usuario.setAtivo(true);
        usuario.setGestor(gestor);
    }

    @Test
    void validarEmailJaExiste_quandoExiste(){
        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        usuarioService.validarEmailJaExiste(usuario.getEmail());
        verify(usuarioRepository).existsByEmail(usuario.getEmail());
    }

    @Test
    void validarEmailJaExiste_quandoNaoExiste(){
        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);
        assertThrows(EmailJaExisteException.class,()->usuarioService.validarEmailJaExiste(usuario.getEmail()));
        verify(usuarioRepository).existsByEmail(usuario.getEmail());
    }

    @Test
    void validarRetornarPorEmail_quandoExiste(){
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));
        Usuario resultado = usuarioService.retornarPorEmail(usuario.getEmail());
        assertEquals(resultado,usuario);
        verify(usuarioRepository).findByEmail(usuario.getEmail());
    }

    @Test
    void validarRetornarPorEmail_quandoNaoExiste(){
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
        assertThrows(UsuarioNaoEncontradoException.class,() -> usuarioService.retornarPorEmail(usuario.getEmail()));
        verify(usuarioRepository).findByEmail(usuario.getEmail());
    }

    @Test
    void validarPesquisarUsuario_quandoExiste(){
        List<Usuario> listaUsuario = List.of(usuario);
        //when(usuarioRepository.findByGestorAndCargoAndNomeContainingIgnoreCase(gestor,cargo,"teste")).thenReturn(listaUsuario);
        List<UsuarioResponseDTO> lista = usuarioService.pesquisarUsuario(new PesquisaUsuarioDTO("teste",cargo.getId()));

    }
}
