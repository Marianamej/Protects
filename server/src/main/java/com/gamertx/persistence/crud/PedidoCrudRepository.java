package com.gamertx.persistence.crud;

import com.gamertx.persistence.entity.products_view.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoCrudRepository extends CrudRepository<Pedido,Integer> {
    Optional<List<Pedido>> findByEmailUsuarioOrderByIdPedido(String email);
    Optional<Pedido> findByEmailUsuario(String email);
}
