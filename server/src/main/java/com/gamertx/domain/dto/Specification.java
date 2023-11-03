package com.gamertx.domain.dto;

import com.gamertx.persistence.entity.products_view.Especificacion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Specification {
    private int idProduct;
    private List<Especificacion> specifications;
}
