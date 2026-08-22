package com.gabriel.bate_ponto.controller;


import com.gabriel.bate_ponto.Service.AuthorizationService;
import com.gabriel.bate_ponto.Service.TokenService;
import com.gabriel.bate_ponto.dto.login.AutenticacaoDTO;
import com.gabriel.bate_ponto.dto.login.LoginResponseDTO;
import com.gabriel.bate_ponto.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> postLogin(@RequestBody @Valid AutenticacaoDTO dto){
        UsernamePasswordAuthenticationToken usuarioEmailSenha = new UsernamePasswordAuthenticationToken(dto.email(),dto.senha());
        Authentication auth = this.authenticationManager.authenticate(usuarioEmailSenha);
        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new LoginResponseDTO(token));

    }


}
