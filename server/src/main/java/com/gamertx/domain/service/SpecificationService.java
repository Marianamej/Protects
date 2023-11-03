package com.gamertx.domain.service;

import com.gamertx.persistence.EspecificacionRepository;
import com.gamertx.persistence.entity.products_view.Especificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationService {
    @Autowired
    private EspecificacionRepository repository;

    public List<String> getAll(int idProducto){

        List <Especificacion> especificacions = repository.getAll(idProducto);
        List <String> especificacionesFormateadas = new ArrayList<>();

        for (Especificacion especificacion: especificacions) {
            String formateo = especificacion.getCaracteristica().getDescripcion();
            especificacionesFormateadas.add(formateo);
        }

        return especificacionesFormateadas;
    }

}
