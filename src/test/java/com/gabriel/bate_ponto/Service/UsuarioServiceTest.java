package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.exceptions.exceptions.EmailJaExisteException;
import com.gabriel.bate_ponto.exceptions.exceptions.UsuarioNaoEncontradoException;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("teste");
        usuario.setEmail("teste@gmail.com");
        usuario.setSenha("teste");
        usuario.setAtivo(true);
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
    void retornarPorEmail_quandoExiste(){
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));
        Usuario resultado = usuarioService.retornarPorEmail(usuario.getEmail());
        assertEquals(resultado,usuario);
        verify(usuarioRepository).findByEmail(usuario.getEmail());
    }
    @Test
    void retornarPorEmail_quandoNaoExiste(){
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
        assertThrows(UsuarioNaoEncontradoException.class,() -> usuarioService.retornarPorEmail(usuario.getEmail()));
        verify(usuarioRepository).findByEmail(usuario.getEmail());
    }
}
