package com.gabriel.bate_ponto.security;

import com.gabriel.bate_ponto.Service.TokenService;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.extrairToken(request); // extrai o token da requisição
        try {
            if (token != null){
                String email = tokenService.validarToken(token); //retorna o email do token da requisição
                UserDetails userDetails = usuarioRepository.findByEmail(email). //consulta no banco o email
                        orElseThrow(()-> new UsernameNotFoundException("Usuerio nao encontrado")); // valia se existe

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                        (userDetails,
                        null,
                        userDetails.getAuthorities());
                // cria a autenticação

                SecurityContextHolder.getContext().setAuthentication(authenticationToken); // registra o login do usuario
            }
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private String extrairToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return null;
        }
        return authHeader.substring(7);
    }
}
