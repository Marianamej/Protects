package com.gamertx.persistance.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    private String email;

    private String nombre;

    private String contraseña;

    private String apellido;

    private String username;

    private Byte edad;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "imagen_perfil")
    private String imgPerfil;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    //Relacion entre Usuario y post
    @OneToMany(mappedBy = "usuario")
    private List<Post> posts;

    //Relacion Pago usuario con usuarios
    @OneToMany(mappedBy = "usuario")
    private List<PagoUsuario> pagosUsuario;
}
