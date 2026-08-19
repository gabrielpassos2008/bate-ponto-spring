package com.gabriel.bate_ponto.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.gabriel.bate_ponto.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String chaveSecreta;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
            String token = JWT.create()
                    .withIssuer("Bate ponto")
                    .withClaim("Role",usuario.getRole().name())
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar o token",exception);
        }
    }
    private Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
