package com.gamertx.domain.service;

import com.gamertx.domain.dto.Comment;
import com.gamertx.domain.dto.Details;
import com.gamertx.persistence.ComentarioRepository;
import com.gamertx.persistence.EspecificacionRepository;
import com.gamertx.persistence.entity.products_view.Especificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailService {
    @Autowired
    private EspecificacionRepository repository;
    @Autowired
    private ComentarioRepository comentarioRepository;
    public Details getAll(int idProducto) {
        List<Comment> comentarios = comentarioRepository.getByProduct(idProducto)
                .orElse(Collections.emptyList());

        List<Especificacion> especificacions = repository.getAll(idProducto);
        List<String> especificacionesFormateadas = especificacions.stream()
                .map(especificacion -> especificacion.getCaracteristica().getDescripcion())
                .collect(Collectors.toList());

        return new Details(comentarios, especificacionesFormateadas);
    }
}
