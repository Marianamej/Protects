package com.gamertx.persistence.entity.products_view;

import com.gamertx.persistence.entity.EnumContent;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "email_usuario")
    private String emailUsuario;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private EnumContent.estadoActual estado;

    @OneToMany(mappedBy = "pedido", cascade = {CascadeType.ALL}, fetch= FetchType.EAGER)
    List<DetallePedido> productos;
}
