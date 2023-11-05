package com.gamertx.persistence.entity.users_view;
import com.gamertx.persistence.entity.payment_view.PagoUsuario;
import com.gamertx.persistence.entity.content_view.Post;
import com.gamertx.utilities.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    private String nombre;

    @Column(nullable = false)
    private String contraseña;

    private String apellido;

    @Column(nullable = false,unique = true)
    private String username;

    private Byte edad;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "imagen_perfil")
    private String imgPerfil;

    @Column(name = "rol",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    //Relacion entre Usuario y post
    @OneToMany(mappedBy = "usuario")
    private List<Post> posts;

    //Relacion Pago usuario con usuarios
    @OneToMany(mappedBy = "usuario")
    private List<PagoUsuario> pagosUsuario;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = role.getPermissions().stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return contraseña;
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
