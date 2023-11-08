package com.gamertx.persistence.entity.users_view;

import com.gamertx.domain.Product;
import com.gamertx.persistence.entity.products_view.Producto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "carrito_compras")
public class CarritoCompras {
    @Id
    @Column(name = "id_carrito")
    private Integer id;

    private Integer cantidad;

    @Column(name = "email_usuario")
    private String emailUsuario;

    @OneToMany(mappedBy = "")
    private List<Producto> productos;
}
