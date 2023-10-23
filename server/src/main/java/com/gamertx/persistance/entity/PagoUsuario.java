package com.gamertx.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Pago_usuario")
public class PagoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_usuario")
    private Integer idPagoUsuario;
    @Column(name = "id_metodo_pago")
    private Integer idMetodoPago;
    @Column(name = "usuarios_email")
    private String emailUsuario;

    //Relacion Metodo de pago con pago usuario
    @ManyToOne
    @JoinColumn(name = "id_metodo_pago",insertable = false, updatable = false)
    private MetodoPago metodoPago;

    //Relacion Pago usuario con usuarios
    @ManyToOne
    @JoinColumn(name = "usuarios_email",insertable = false,updatable = false)
    private Usuario usuario;
}