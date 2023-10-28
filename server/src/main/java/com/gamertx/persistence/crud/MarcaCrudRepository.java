package com.gamertx.persistence.crud;

import com.gamertx.persistence.entity.products_view.Marca;
import org.springframework.data.repository.CrudRepository;

public interface MarcaCrudRepository extends CrudRepository<Marca,Integer> {

}
