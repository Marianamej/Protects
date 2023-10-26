package com.gamertx.persistence.entity.payment_view;

import com.gamertx.persistence.entity.EnumContent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Metodo_pago")
public class MetodoPago {
    @Id
    @Column(name = "id_metodo_pago")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMetodoPago;

    private String titular;

    @Column(name = "banco_emisor")
    private String bancoEmisor;

    private String numero_tarjeta;
    private String numero_cuenta;

    @Enumerated(EnumType.STRING)
    private EnumContent.OpcionesPago metodoPago;

    //Relacion Metodo de pago con pago usuario
    @OneToMany(mappedBy = "metodoPago")
    private List<PagoUsuario> pagosUsuarios;

}
