package com.gabriel.bate_ponto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String senha;
    private String nome;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "fk_gestor_id")
    private Usuario gestor;

    @OneToMany(mappedBy = "gestor")
    private List<Usuario> subordinados;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.ROLE_ADMIN) {
            return List.of( new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_COLABORADOR"),
            new SimpleGrantedAuthority("ROLE_GESTOR"));
        }
        if (this.role == Role.ROLE_GESTOR){
            return List.of(new SimpleGrantedAuthority("ROLE_GESTOR"),
            new SimpleGrantedAuthority("ROLE_COLABORADOR"));
        }

        return List.of(new SimpleGrantedAuthority("ROLE_COLABORADOR"));
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
